package com.alpamedev.weather.common.dataAccess

import com.alpamedev.weather.common.entities.Forecast
import com.alpamedev.weather.common.entities.Weather
import com.alpamedev.weather.common.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET(Constants.WEATHER_CALL_PATH)
    suspend fun getCurrentWeatherByCoordinates(
        @Query(Constants.LATITUDE_PARAM) latitude: Double,
        @Query(Constants.LONGITUDE_PARAM) longitude: Double,
        @Query(Constants.APP_ID_PARAM) apiKey: String,
        @Query(Constants.UNITS_PARAM) units: String,
        @Query(Constants.LANGUAGE_PARAM) language: String
    ): Weather
    @GET(Constants.FORECAST_WEATHER_CALL_PATH)
    suspend fun getForecastWeatherByCoordinates(
        @Query(Constants.LATITUDE_PARAM) latitude: Double,
        @Query(Constants.LONGITUDE_PARAM) longitude: Double,
        @Query(Constants.APP_ID_PARAM) apiKey: String,
        @Query(Constants.UNITS_PARAM) units: String,
        @Query(Constants.COUNT_PARAM) count: Int,
        @Query(Constants.LANGUAGE_PARAM) language: String
    ): Forecast
}