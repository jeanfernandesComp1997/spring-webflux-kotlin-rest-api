package com.example.springwebfluxkotlinrestapi.domain.dtos

data class CharacterDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocationDTO,
    val location: CharacterLocationDTO,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)


