package com.example.springwebfluxkotlinrestapi.domain.dtos

data class LocationDTO(
    val id: Int,
    val name: String,
    val type: String?,
    val dimension: String?,
    val created: String
)