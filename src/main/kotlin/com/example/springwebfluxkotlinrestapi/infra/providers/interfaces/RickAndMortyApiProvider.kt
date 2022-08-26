package com.example.springwebfluxkotlinrestapi.infra.providers.interfaces

import com.example.springwebfluxkotlinrestapi.domain.dtos.CharacterDTO
import com.example.springwebfluxkotlinrestapi.domain.dtos.LocationDTO
import com.example.springwebfluxkotlinrestapi.domain.dtos.RickAndMortyFindAllResponse
import reactor.core.publisher.Mono

interface RickAndMortyApiProvider {

    fun findAll(): Mono<RickAndMortyFindAllResponse>
    fun findById(id: Int): Mono<CharacterDTO>
    fun findLocationById(id: Int): Mono<LocationDTO>
}