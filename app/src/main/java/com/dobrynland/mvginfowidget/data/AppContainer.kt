package com.dobrynland.mvginfowidget.data

import com.dobrynland.mvginfowidget.network.MvgApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class AppContainer {
    private val BASE_URL = "https://www.mvg.de/"
    var JSON = Json { ignoreUnknownKeys = true; prettyPrint = true }
    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(JSON.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: MvgApiService by lazy {
        retrofit.create(MvgApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    val departureInfoRepository: DepartureInfoRepository by lazy {
        NetworkDepartureInfoRepository(retrofitService)
    }
}