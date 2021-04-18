package com.axel.hogwartslibrary.data.network

import com.axel.hogwartslibrary.data.services.CharactersService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitFactory {

    companion object {
        val instance = RetrofitFactory()
    }

    private fun okHttpInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private val json = Json {
        encodeDefaults = true
        prettyPrint = true  // Читабельные отступы при показе json
        ignoreUnknownKeys = true  // Игнорировать неизвестные поля в Json объекте
        isLenient = true
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(okHttpInterceptor())
        .build()

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl("https://hp-api.herokuapp.com/api/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

        .build()

    val charactersService: CharactersService = retrofitClient.create(CharactersService::class.java)
}