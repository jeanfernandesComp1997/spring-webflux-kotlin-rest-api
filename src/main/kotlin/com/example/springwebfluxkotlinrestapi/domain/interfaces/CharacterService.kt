package com.example.springwebfluxkotlinrestapi.domain.interfaces

import com.example.springwebfluxkotlinrestapi.domain.dtos.CharacterDTO
import reactor.core.publisher.Flux

interface CharacterService {

    suspend fun findAll(): List<CharacterDTO>
    fun findAllAsync(): Flux<CharacterDTO>
}