package com.alpamedev.weather.common.entities

import com.google.gson.annotations.SerializedName

data class Weather(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    @SerializedName("coord") val coordinates: Coordinates,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val rain: Rain,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
)