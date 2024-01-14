package com.alpamedev.weather.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpamedev.weather.common.entities.Weather
import com.alpamedev.weather.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    private val _result = MutableLiveData<Weather>()
    val result : LiveData<Weather>
        get() = _result

    private val _snackBarMessage = MutableLiveData<Int>()
    val snackBarMessage: LiveData<Int>
        get() = _snackBarMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _result.value = repository.getWeatherForecast(lat, lon, "metric", "sp, es")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}