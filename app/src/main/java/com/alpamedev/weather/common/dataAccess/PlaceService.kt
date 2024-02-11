package com.alpamedev.weather.common.dataAccess

import com.alpamedev.weather.common.entities.Place
import com.alpamedev.weather.common.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET(Constants.PLACE_CALL_PATH)
    suspend fun getCity(
        @Query(Constants.Q_PARAM) q: String,
        @Query(Constants.LIMIT_PARAM) limit: Int,
        @Query(Constants.APP_ID_PARAM) apiKey: String
    ): List<Place>
}