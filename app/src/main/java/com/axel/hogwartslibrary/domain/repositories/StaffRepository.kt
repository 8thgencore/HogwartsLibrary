package com.axel.hogwartslibrary.domain.repositories;

import com.axel.hogwartslibrary.domain.models.CharacterModel


interface StaffRepository {
    suspend fun getAllStaff(): List<CharacterModel>
}