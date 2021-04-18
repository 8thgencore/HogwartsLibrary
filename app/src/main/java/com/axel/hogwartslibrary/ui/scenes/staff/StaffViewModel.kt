package com.axel.hogwartslibrary.ui.scenes.staff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axel.hogwartslibrary.domain.repositories.StaffRepositoryImpl
import com.axel.hogwartslibrary.ui.scenes.staff.adapters.StaffCellModel
import com.axel.hogwartslibrary.ui.scenes.staff.adapters.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StaffViewModel : ViewModel() {

    private val staffRepository = StaffRepositoryImpl()

    //        value = mutableListOf(
//            StaffCellModel(name = "Diffindo", type = "Charm"),
//            StaffCellModel(name = "Vingardio Leviosa", type = "Spell"),
//            StaffCellModel(name = "Avada Kedavra", type = "Curse"),
//            StaffCellModel(name = "Oblivios", type = "Jinx"),
//            StaffCellModel(name = "Diffindo", type = "Charm"),
//            StaffCellModel(name = "Vingardio Leviosa", type = "Spell"),
//            StaffCellModel(name = "Avada Kedavra", type = "Curse"),
//            StaffCellModel(name = "Oblivios", type = "Jinx")
//        )

    private val _staffs =
        MutableLiveData<MutableList<StaffCellModel>>().apply { value = mutableListOf() }
    private val _filters =
        MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }
    private val _staffsDisplay =
        MutableLiveData<MutableList<StaffCellModel>>().apply { value = ArrayList() }

    //////////////////////////////////////////////////////////////////////////////////////

    val staffsDisplay: LiveData<MutableList<StaffCellModel>> = _staffsDisplay

    init {
        fetchStaff()
    }

    private fun fetchStaff() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val staff = staffRepository.getAllStaff()
                _staffs.postValue(staff.map { it.mapToUI() }.toMutableList())
                _staffsDisplay.postValue(_staffs.value ?: ArrayList())
            }
        }
    }

    fun pressFilter(type: String, isSelected: Boolean) {
        if (isSelected) {
            _filters.value?.add(type)
        } else {
            _filters.value?.remove(type)
        }

        if (_filters.value?.isEmpty() == true) {
            _staffsDisplay.postValue(_staffs.value ?: ArrayList())
            return
        }

        _staffsDisplay.postValue(_staffs.value?.filter { _filters.value?.contains(it.species) ?: false }?.toMutableList())
    }
}
