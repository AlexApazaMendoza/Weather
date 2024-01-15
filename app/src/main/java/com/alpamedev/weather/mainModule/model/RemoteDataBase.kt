package com.alpamedev.weather.mainModule.model

import com.alpamedev.weather.common.dataAccess.RetrofitConfig
import com.alpamedev.weather.common.entities.Forecast
import com.alpamedev.weather.common.entities.Weather
import com.alpamedev.weather.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataBase {
    suspend fun getCurrentWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        units: String,
        language: String
    ): Weather = withContext(Dispatchers.IO) {
        RetrofitConfig.weatherService.getCurrentWeatherByCoordinates(
            latitude,
            longitude,
            Constants.APP_ID,
            units,
            language
        )
    }
    suspend fun getForecastWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        units: String,
        count:Int,
        language: String
    ): Forecast = withContext(Dispatchers.IO) {
        RetrofitConfig.weatherService.getForecastWeatherByCoordinates(
            latitude,
            longitude,
            Constants.APP_ID,
            units,
            count,
            language
        )
    }
}