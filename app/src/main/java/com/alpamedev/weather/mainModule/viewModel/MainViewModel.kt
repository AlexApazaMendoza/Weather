package com.alpamedev.weather.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpamedev.weather.R
import com.alpamedev.weather.common.entities.Forecast
import com.alpamedev.weather.common.entities.Weather
import com.alpamedev.weather.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    private val _result = MutableLiveData<Weather>()
    val result : LiveData<Weather>
        get() = _result

    private val _forecast = MutableLiveData<Forecast>()
    val forecast : LiveData<Forecast>
        get() = _forecast

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
                _result.value = repository.getCurrentWeather(lat, lon, "metric", "sp, es")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun getForecastWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val forecast = repository.getForecastWeather(lat, lon, "metric", 6, "sp, es")
                _forecast.value = forecast
                if (forecast.list.isEmpty()) {
                    _snackBarMessage.value = R.string.weather_empty_list_message
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}