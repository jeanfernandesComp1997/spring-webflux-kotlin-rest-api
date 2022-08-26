package com.example.springwebfluxkotlinrestapi.domain.services

import com.example.springwebfluxkotlinrestapi.domain.dtos.CharacterDTO
import com.example.springwebfluxkotlinrestapi.domain.dtos.views.CharacterWithFullLocationView
import com.example.springwebfluxkotlinrestapi.domain.interfaces.CharacterService
import com.example.springwebfluxkotlinrestapi.infra.providers.interfaces.RickAndMortyApiProvider
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.collect
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CharacterServiceImpl(
    private val rickAndMortyApiProvider: RickAndMortyApiProvider
) : CharacterService {

    override suspend fun findAll(): List<CharacterDTO> {
//        Usar o block quando estiver utilizando Tomcat, ou a lib web do spring para esperar o resultado da chamada
//        val characters = rickAndMortyApiProvider
//            .findAll()
//            .block()
//
//        return characters?.results ?: throw Exception()

        val result = rickAndMortyApiProvider
            .findAll()
            .awaitSingle()

        return result.results
    }

    override fun findAllAsync(): Flux<CharacterDTO> {
        val response = rickAndMortyApiProvider.findAll()

        return response.flatMapMany { r ->
            Flux.fromIterable(r.results)
        }
    }

    override suspend fun testSuspendFlux(): List<CharacterDTO> {
        return findAllAsync().collectList().awaitSingle()
    }

    override fun findByIdAsync(id: Int): Mono<CharacterDTO> {
        return rickAndMortyApiProvider.findById(id)
    }

    override fun findByIdBatchAsync(ids: List<Int>): Flux<CharacterDTO> {
        return Flux.fromIterable(ids)
            .flatMap { id ->
                findByIdAsync(id)
            }
    }

    override suspend fun findByIdBatch(ids: List<Int>): List<CharacterDTO> {
        return ids.map { id ->
            rickAndMortyApiProvider.findById(id).awaitSingle()
        }
    }

    override suspend fun findByIdWithFullLocation(id: Int): CharacterWithFullLocationView {
        val character = rickAndMortyApiProvider.findById(id).awaitSingleOrNull()
            ?: throw Exception("Character Not Found")

        val originId = character.location.url.let {
            val lastIndex = it.lastIndexOf("/")
            it.substring(lastIndex + 1)
        }.toInt()

        val locationId = character.origin.url.let {
            val lastIndex = it.lastIndexOf("/")
            it.substring(lastIndex + 1)
        }.toInt()

        val origin = rickAndMortyApiProvider.findLocationById(originId)
        val location = rickAndMortyApiProvider.findLocationById(locationId)

        val result = Mono.zip(origin, location).awaitSingle()

        return CharacterWithFullLocationView(
            character,
            result.t1,
            result.t2
        )
    }
}