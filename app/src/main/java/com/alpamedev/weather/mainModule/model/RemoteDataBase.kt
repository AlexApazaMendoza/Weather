package com.alpamedev.weather.mainModule.model

import com.alpamedev.weather.common.dataAccess.RetrofitConfig
import com.alpamedev.weather.common.entities.Weather
import com.alpamedev.weather.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataBase {
    suspend fun getWeatherForecastByCoordinates(
        latitude: Double,
        longitude: Double,
        units: String,
        language: String
    ): Weather = withContext(Dispatchers.IO) {
        RetrofitConfig.weatherService.getWeatherForecastByCoordinates(
            latitude,
            longitude,
            Constants.APP_ID,
            units,
            language
        )
    }
}