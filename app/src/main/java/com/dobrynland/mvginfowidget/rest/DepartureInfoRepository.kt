package com.dobrynland.mvginfowidget.rest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dobrynland.mvginfowidget.data.DepartureInfo
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DepartureInfoRepository {
    private val contentType = "application/json".toMediaType()
    var json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    private val webservice: RestApi by lazy {

        Retrofit.Builder()
            .baseUrl("https://www.mvg.de/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build().create(RestApi::class.java)
    }

    private var client: RestApi = webservice

    fun getDepartures(): LiveData<DepartureInfo> {
        val liveData = MutableLiveData<DepartureInfo>()

        client.getDepartureInfoList().enqueue(object: Callback<DepartureInfo> {
            override fun onResponse(call: Call<DepartureInfo>, response: Response<DepartureInfo>) {
                if (response.isSuccessful) {

                    // When data is available, populate LiveData
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<DepartureInfo>, t: Throwable) {
                t.printStackTrace()
            }
        })

        // Synchronously return LiveData
        // Its value will be available onResponse
        return liveData
    }
}