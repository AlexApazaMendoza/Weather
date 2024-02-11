package com.alpamedev.weather.common.utils

import com.alpamedev.weather.BuildConfig

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/"
    const val WEATHER_CALL_PATH = "data/2.5/weather"
    const val PLACE_CALL_PATH = "geo/1.0/direct"
    const val FORECAST_WEATHER_CALL_PATH = "data/2.5/forecast"
    const val APP_ID = BuildConfig.API_OWM_KEY

    const val LATITUDE_PARAM = "lat"
    const val LONGITUDE_PARAM = "lon"
    const val APP_ID_PARAM = "appid"
    const val MODE_PARAM = "mode"
    const val COUNT_PARAM = "cnt"
    const val UNITS_PARAM = "units"
    const val LANGUAGE_PARAM = "lang"
    const val LIMIT_PARAM = "limit"
    const val Q_PARAM = "q"
}