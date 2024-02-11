package com.alpamedev.weather.mainModule.model

import com.alpamedev.weather.common.entities.Forecast
import com.alpamedev.weather.common.entities.Place
import com.alpamedev.weather.common.entities.Weather

class MainRepository {
    private val remoteDataBase = RemoteDataBase()

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        language: String
    ): Weather = remoteDataBase.getCurrentWeatherByCoordinates(
        latitude,
        longitude,
        units,
        language
    )
    suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double,
        units: String,
        count: Int,
        language: String
    ): Forecast = remoteDataBase.getForecastWeatherByCoordinates(
        latitude,
        longitude,
        units,
        count,
        language
    )
    suspend fun getPlace(
        place: String
    ): List<Place> = remoteDataBase.getCity(place)
}