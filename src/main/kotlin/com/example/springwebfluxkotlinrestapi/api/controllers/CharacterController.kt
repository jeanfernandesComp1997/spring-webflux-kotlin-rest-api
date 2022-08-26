package com.example.springwebfluxkotlinrestapi.api.controllers

import com.example.springwebfluxkotlinrestapi.domain.dtos.CharacterDTO
import com.example.springwebfluxkotlinrestapi.domain.dtos.views.CharacterWithFullLocationView
import com.example.springwebfluxkotlinrestapi.domain.interfaces.CharacterService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("characters")
class CharacterController(
    private val characterService: CharacterService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun list(): List<CharacterDTO> {
        return characterService.findAll()
    }

    @GetMapping("async")
    @ResponseStatus(HttpStatus.OK)
    fun listAsync(): Flux<CharacterDTO> {
        return characterService.findAllAsync()
    }

    @GetMapping("block-flux")
    @ResponseStatus(HttpStatus.OK)
    suspend fun listBlockingFlux(): List<CharacterDTO> {
        return characterService.testSuspendFlux()
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findByIdAsync(@PathVariable id: Int): Mono<CharacterDTO> {
        return characterService.findByIdAsync(id)
    }

    @GetMapping("batch-async")
    @ResponseStatus(HttpStatus.OK)
    fun findByIdBatchAsync(@RequestParam(required = true) ids: List<Int>): Flux<CharacterDTO> {
        return characterService.findByIdBatchAsync(ids)
    }

    @GetMapping("batch")
    @ResponseStatus(HttpStatus.OK)
    suspend fun findByIdBatch(@RequestParam(required = true) ids: List<Int>): List<CharacterDTO> {
        return characterService.findByIdBatch(ids)
    }

    @GetMapping("full-location/{id}")
    @ResponseStatus(HttpStatus.OK)
    suspend fun findByIdFullLocation(@PathVariable id: Int): CharacterWithFullLocationView {
        return characterService.findByIdWithFullLocation(id)
    }
}