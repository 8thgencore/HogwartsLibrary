package com.axel.hogwartslibrary.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Wand (
    val wood : String = "",
    val core : String = "",
    val length : String? = null
)

@Serializable
data class CharacterRemote(
    val name : String,
    val species : String,
    val gender : String,
    val house : String,
    val dateOfBirth : String = "",
    val yearOfBirth : String? = null,
    val ancestry : String = "",
    val eyeColour : String = "",
    val hairColour : String = "",
    val wand : Wand,
    val patronus : String = "",
    val hogwartsStudent : Boolean,
    val hogwartsStaff : Boolean,
    val actor : String,
    val alive : Boolean,
    val image : String
)