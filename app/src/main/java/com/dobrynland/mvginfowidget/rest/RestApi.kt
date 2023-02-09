package com.dobrynland.mvginfowidget.rest

import com.dobrynland.mvginfowidget.data.DepartureInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestApi {

    @Headers("Content-Type: application/json")
    @GET("/de:09184:2310")
    suspend fun getDepartureInfoList(): Response<List<DepartureInfo>>
}