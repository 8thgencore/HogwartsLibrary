package com.axel.hogwartslibrary.domain.repositories

import com.axel.hogwartslibrary.domain.models.FacultyModel
import kotlinx.coroutines.delay

class HatRepositoryImpl: HatRepository {
    override suspend fun generateFaculty(username: String): FacultyModel {
        delay(1000)

        return if (username == "Harry Potter") {
            FacultyModel(name = "Griffindor")
        } else {
            FacultyModel(name = "Slytherin")
        }
    }
}