package com.alpamedev.weather.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpamedev.weather.common.entities.Place
import com.alpamedev.weather.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val repository = MainRepository()

    val text = MutableLiveData<String>("")

    private val _result = MutableLiveData<List<Place>>(mutableListOf())
    val result: LiveData<List<Place>>
        get() = _result

    private val _data = MutableLiveData<MutableList<String>>(mutableListOf())
    val data: LiveData<MutableList<String>>
        get() = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun searchPlace(place: String) {
        if (place.isEmpty()) {
            _result.value = mutableListOf()
        } else {
            _isLoading.value = true
            viewModelScope.launch {
                _result.value = repository.getPlace(place)
            }
        }
    }

    fun updateData(data: List<Place>) {
        if (data.isEmpty()) {
            _data.value = mutableListOf()
        } else {
            _data.value = data.map { place ->
                val state = if (place.state != null) "(${place.state})" else ""
                "${place.name} $state [${place.country}]"
            }.toMutableList()
        }
        _isLoading.value = false
    }
}