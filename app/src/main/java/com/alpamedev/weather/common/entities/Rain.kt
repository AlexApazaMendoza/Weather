package com.alpamedev.weather.common.entities

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h") val oneHour: Double
)