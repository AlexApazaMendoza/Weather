package com.alpamedev.weather.common.entities

import com.google.gson.annotations.SerializedName

data class ForecastX(
    val clouds: Clouds,
    val dt: Int,
    @SerializedName("dt_txt")val dtTxt: String,
    val main: MainForecast,
    val pop: Double,
    val rain: RainForecast,
    val sys: SysForecast,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
)

data class MainForecast(
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("grnd_level") val groundLevel: Int,
    val humidity: Int,
    val pressure: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    val temp: Double,
    @SerializedName("temp_kf")val tempKf: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("temp_min") val tempMin: Double
)

data class RainForecast(
    @SerializedName("3h")val threeHour: Double
)

data class SysForecast(
    val pod: String
)
