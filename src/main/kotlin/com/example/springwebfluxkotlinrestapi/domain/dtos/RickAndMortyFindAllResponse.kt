package com.example.springwebfluxkotlinrestapi.domain.dtos

data class RickAndMortyFindAllResponse(
    val info: Info,
    val results: List<CharacterDTO>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String?
)
