package com.alpamedev.weather.common.entities

import com.google.gson.annotations.SerializedName

data class Place(
    val name: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    val country: String,
    val state: String?
)