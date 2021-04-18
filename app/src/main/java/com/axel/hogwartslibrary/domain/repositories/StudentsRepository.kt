package com.axel.hogwartslibrary.domain.repositories

import com.axel.hogwartslibrary.domain.models.CharacterModel

interface StudentsRepository {
    suspend fun fetchStudents(): List<CharacterModel>
}