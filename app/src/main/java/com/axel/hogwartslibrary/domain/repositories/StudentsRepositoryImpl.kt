package com.axel.hogwartslibrary.domain.repositories

import com.axel.hogwartslibrary.data.network.RetrofitFactory
import com.axel.hogwartslibrary.domain.models.CharacterModel
import com.axel.hogwartslibrary.domain.models.mapToModel

class StudentsRepositoryImpl : StudentsRepository {
    override suspend fun fetchStudents(): List<CharacterModel> {
        return RetrofitFactory.instance.charactersService.getAllStudents()
            .filter { it.hogwartsStudent }
            .filter { it.house.isNotEmpty() }
            .map { it.mapToModel() }


//        return listOf(
//            StudentModel(id = 0, name = "Harry", secondName = "Potter", facultyName = "Griffindor"),
//            StudentModel(id = 1, name = "Ronald", secondName = "Whisley", facultyName = "Griffindor"),
//            StudentModel(id = 2, name = "Drako", secondName = "Malfoy", facultyName = "Slytherin"),
//            StudentModel(id = 0, name = "Sedrik", secondName = "Diggori", facultyName = "Ravenclaw")
//        )
    }
}