package com.axel.hogwartslibrary.domain.models

import com.axel.hogwartslibrary.data.models.CharacterRemote

data class CharacterModel(
    val name: String,
    val species: String,
    val dateOfBirth: String,
    val house: String,
    val ancestry: String,
    val image: String
)

fun CharacterRemote.mapToModel(): CharacterModel {
    return CharacterModel(
        name = this.name,
        species = this.species,
        dateOfBirth = this.dateOfBirth,
        house = this.house,
        ancestry = this.ancestry,
        image = this.image
    )
}
