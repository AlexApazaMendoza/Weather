package com.alpamedev.weather.common.entities

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastX>,
    val message: Int
)