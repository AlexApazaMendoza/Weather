package com.alpamedev.weather.common.utils

import com.alpamedev.weather.common.entities.WeatherX
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun getHour(epoch: Long, timeZone: Int?): String {
    return getFormatTime(epoch, "HH:mm", timeZone)
}

fun getDateTime(epoch: Long, timeZone: Int?): String {
    return getFormatTime(epoch, "dd/MM/yy HH:mm", timeZone)
}

fun getFormatTime(epoch: Long, format: String, timeZone: Int?): String {
    val customTimeZone = if (timeZone == null) {
        TimeZone.getDefault()
    } else {
        val timeZoneOffsetMillis = timeZone * 1000L
        TimeZone.getTimeZone("GMT").apply {
            rawOffset = timeZoneOffsetMillis.toInt()
        }
    }
    return SimpleDateFormat(format, Locale.getDefault())
        .apply {
            this.timeZone = customTimeZone
        }.format(epoch * 1_000L)
}

fun getWeatherMain(weather: List<WeatherX>?): String {
    return if(weather?.isNotEmpty() == true) weather[0].main else "-"
}

fun getWeatherDescription(weather: List<WeatherX>?): String {
    return if(weather?.isNotEmpty() == true) weather[0].description else "-"
}

fun getPop(value: Double): Double {
    return value * 100
}
