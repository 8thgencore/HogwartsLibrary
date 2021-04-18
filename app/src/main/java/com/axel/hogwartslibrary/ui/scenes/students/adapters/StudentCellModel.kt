package com.axel.hogwartslibrary.ui.scenes.students.adapters

import com.axel.hogwartslibrary.domain.models.CharacterModel

data class StudentCellModel(
    val name: String,
    val house: String,
    val dateOfBirth: String,
    val ancestry: String,
    val image: String
)

fun CharacterModel.mapToUI(): StudentCellModel {
    return StudentCellModel(
        name = this.name,
        house = this.house,
        dateOfBirth = this.dateOfBirth,
        ancestry = this.ancestry,
        image = this.image
    )
}