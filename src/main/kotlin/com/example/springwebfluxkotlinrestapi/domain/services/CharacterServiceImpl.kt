package com.example.springwebfluxkotlinrestapi.domain.services

import com.example.springwebfluxkotlinrestapi.domain.dtos.CharacterDTO
import com.example.springwebfluxkotlinrestapi.domain.interfaces.CharacterService
import com.example.springwebfluxkotlinrestapi.infra.providers.interfaces.RickAndMortyApiProvider
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux

@Service
class CharacterServiceImpl(
    private val rickAndMortyApiProvider: RickAndMortyApiProvider
) : CharacterService {

    override suspend fun findAll(): List<CharacterDTO> {
//        val characters = rickAndMortyApiProvider
//            .findAll()
//            .block()
//
//        return characters?.results ?: throw Exception()

        val result =
            rickAndMortyApiProvider
                .findAll()
                .awaitSingle()

        return result.results
    }

    override fun findAllAsync(): Flux<CharacterDTO> {
        val response = rickAndMortyApiProvider.findAll()

        val characters = response.flatMapMany { r ->
            Flux.fromIterable(r.results)
        }

        return characters
    }
}