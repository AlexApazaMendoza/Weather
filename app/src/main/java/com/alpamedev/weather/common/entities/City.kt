package com.alpamedev.weather.common.entities

import com.alpamedev.weather.common.entities.Coordinates
import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("coord") val coordinates: Coordinates,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)