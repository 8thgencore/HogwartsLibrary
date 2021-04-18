package com.axel.hogwartslibrary.data.services

import com.axel.hogwartslibrary.data.models.CharacterRemote
import retrofit2.http.GET

interface CharactersService {

    @GET("./characters/students")
    suspend fun getAllStudents(): List<CharacterRemote>

    @GET("./characters/staff")
    suspend fun getAllStaff(): List<CharacterRemote>



}