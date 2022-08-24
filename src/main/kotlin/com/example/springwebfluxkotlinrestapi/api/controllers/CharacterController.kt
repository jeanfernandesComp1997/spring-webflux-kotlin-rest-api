package com.example.springwebfluxkotlinrestapi.api.controllers

import com.example.springwebfluxkotlinrestapi.domain.interfaces.CharacterService
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("characters")
class CharacterController(
    private val characterService: CharacterService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun list(): Any {
        return characterService.findAll()
    }

    @GetMapping("async")
    @ResponseStatus(HttpStatus.OK)
    fun listAsync(): Any {
        return characterService.findAllAsync()
    }
}