package com.alpamedev.weather.common.entities

import com.google.gson.annotations.SerializedName

data class Weather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    @SerializedName("coord") val coordinates: Coordinates,
    val dt: Int,
    val id: Int,
    val main: MainWeather,
    val name: String,
    val rain: RainWeather,
    val sys: SysWeather,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
)

data class MainWeather(
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("grnd_level") val groundLevel: Int,
    val humidity: Int,
    val pressure: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    val temp: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("temp_min") val tempMin: Double
)

data class RainWeather(
    @SerializedName("1h") val oneHour: Double
)

data class SysWeather(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)