package com.axel.hogwartslibrary.ui.scenes.staff.adapters

import com.axel.hogwartslibrary.domain.models.CharacterModel


data class StaffCellModel(
    val name: String,
    val species: String,
    // val image: String
)

fun CharacterModel.mapToUI(): StaffCellModel {
    return StaffCellModel(
        name = this.name,
        species = this.species,
        // image = this.image
    )
}