package com.alpamedev.weather.mainModule.model

import com.alpamedev.weather.common.entities.Weather

class MainRepository {
    private val remoteDataBase = RemoteDataBase()

    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        units: String,
        language: String
    ): Weather = remoteDataBase.getWeatherForecastByCoordinates(
        latitude,
        longitude,
        units,
        language
    )
}