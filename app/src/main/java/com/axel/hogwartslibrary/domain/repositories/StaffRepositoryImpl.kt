package com.axel.hogwartslibrary.domain.repositories

import com.axel.hogwartslibrary.data.network.RetrofitFactory
import com.axel.hogwartslibrary.domain.models.CharacterModel
import com.axel.hogwartslibrary.domain.models.mapToModel

class StaffRepositoryImpl: StaffRepository {
    override suspend fun getAllStaff(): List<CharacterModel> {
        return RetrofitFactory.instance.charactersService.getAllStaff()
            .map { it.mapToModel() }
    }
}