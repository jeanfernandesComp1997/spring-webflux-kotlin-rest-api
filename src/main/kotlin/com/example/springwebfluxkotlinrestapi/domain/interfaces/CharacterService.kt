package com.example.springwebfluxkotlinrestapi.domain.interfaces

import com.example.springwebfluxkotlinrestapi.domain.dtos.CharacterDTO
import com.example.springwebfluxkotlinrestapi.domain.dtos.views.CharacterWithFullLocationView
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CharacterService {

    suspend fun findAll(): List<CharacterDTO>
    fun findAllAsync(): Flux<CharacterDTO>
    suspend fun testSuspendFlux(): List<CharacterDTO>
    fun findByIdAsync(id: Int): Mono<CharacterDTO>
    fun findByIdBatchAsync(ids: List<Int>): Flux<CharacterDTO>
    suspend fun findByIdBatch(ids: List<Int>): List<CharacterDTO>
    suspend fun findByIdWithFullLocation(id: Int): CharacterWithFullLocationView
}