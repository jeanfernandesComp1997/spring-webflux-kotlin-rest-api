package com.example.springwebfluxkotlinrestapi.infra.providers.interfaces

import com.example.springwebfluxkotlinrestapi.domain.dtos.RickAndMortyFindAllResponse
import reactor.core.publisher.Mono

interface RickAndMortyApiProvider {

    fun findAll(): Mono<RickAndMortyFindAllResponse>
}