package com.example.springwebfluxkotlinrestapi.domain.dtos.views

import com.example.springwebfluxkotlinrestapi.domain.dtos.CharacterDTO
import com.example.springwebfluxkotlinrestapi.domain.dtos.LocationDTO

class CharacterWithFullLocationView(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDTO,
    val location: LocationDTO,
    val image: String,
    val created: String
) {
    constructor(characterDTO: CharacterDTO, origin: LocationDTO, location: LocationDTO) : this(
        characterDTO.id,
        characterDTO.name,
        characterDTO.status,
        characterDTO.species,
        characterDTO.type,
        characterDTO.gender,
        origin,
        location,
        characterDTO.image,
        characterDTO.created
    )
}
