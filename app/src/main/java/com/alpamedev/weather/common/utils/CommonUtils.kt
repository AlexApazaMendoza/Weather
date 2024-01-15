package com.alpamedev.weather.common.utils

import com.alpamedev.weather.common.entities.WeatherX
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun getHour(epoch: Long): String {
    return getFormatTime(epoch, "HH:mm")
}

fun getDateTime(epoch: Long): String {
    return getFormatTime(epoch, "dd/MM/yy HH:mm")
}

fun getFormatTime(epoch: Long, format: String): String = SimpleDateFormat(format, Locale.getDefault())
    .apply {
        timeZone = TimeZone.getDefault()
    }.format(epoch * 1_000L)

fun getWeatherMain(weather: List<WeatherX>?): String {
    return if(weather?.isNotEmpty() == true) weather[0].main else "-"
}

fun getWeatherDescription(weather: List<WeatherX>?): String {
    return if(weather?.isNotEmpty() == true) weather[0].description else "-"
}
