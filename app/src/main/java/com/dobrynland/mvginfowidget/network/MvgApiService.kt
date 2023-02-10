package com.dobrynland.mvginfowidget.network

import com.dobrynland.mvginfowidget.model.DepartureInfo
import retrofit2.http.GET
import retrofit2.http.Headers

interface MvgApiService {
    @Headers("Content-Type: application/json")
    @GET("/api/fahrinfo/departure/de:09184:2310/")
    suspend fun getDepartureInfo(): DepartureInfo
}