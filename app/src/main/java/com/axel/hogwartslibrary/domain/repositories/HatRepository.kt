package com.axel.hogwartslibrary.domain.repositories

import com.axel.hogwartslibrary.domain.models.FacultyModel

interface HatRepository {
    suspend fun generateFaculty(username: String): FacultyModel
}