package com.axel.hogwartslibrary.ui.scenes.house

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axel.hogwartslibrary.R

class HouseDetailViewModel : ViewModel() {

    private val _houseName: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _founder: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _leader: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _ghost: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _houseImage: MutableLiveData<Int> =
        MutableLiveData<Int>().apply { value = R.drawable.img_gryffindor }

    val houseName: LiveData<String> = _houseName
    val founder: LiveData<String> = _founder
    val leader: LiveData<String> = _leader
    val ghost: LiveData<String> = _ghost
    val houseImage: LiveData<Int> = _houseImage

    fun fetchData(house: Houses?) {
        when (house) {
            Houses.Gryffindor -> {
                _houseName.postValue("Gryffindor")
                _founder.postValue("Godric Gryffindor")
                _leader.postValue("Minerva McGonagall")
                _ghost.postValue("Nearly-Headless Nick")
                _houseImage.postValue(R.drawable.img_gryffindor)
            }
            Houses.Hufflepuff -> {
                _houseName.postValue("Hufflepuff")
                _founder.postValue("Helga Hufflepuff")
                _leader.postValue("Pomona Sprout")
                _ghost.postValue("Fat Friar")
                _houseImage.postValue(R.drawable.img_hufflepuff)
            }
            Houses.Ravenclaw -> {
                _houseName.postValue("Ravenclaw")
                _founder.postValue("Rowena Ravenclaw")
                _leader.postValue("Filius Flitwick")
                _ghost.postValue("Grey Lady")
                _houseImage.postValue(R.drawable.img_ravenclaw)
            }
            else -> {
                _houseName.postValue("Slytherin")
                _founder.postValue("Salazar Slytherin")
                _leader.postValue("Severus Snape")
                _ghost.postValue("Bloody Baron")
                _houseImage.postValue(R.drawable.img_slytherin)
            }
        }
    }
}