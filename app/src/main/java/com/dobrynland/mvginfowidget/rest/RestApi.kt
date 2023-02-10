package com.dobrynland.mvginfowidget.rest

import com.dobrynland.mvginfowidget.data.DepartureInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestApi {

    @Headers("Content-Type: application/json")
    @GET("/api/fahrinfo/departure/de:09184:2310/")
    fun getDepartureInfoList(): Call<DepartureInfo>
}