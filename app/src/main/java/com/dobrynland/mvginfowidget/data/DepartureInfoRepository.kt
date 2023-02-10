package com.dobrynland.mvginfowidget.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dobrynland.mvginfowidget.rest.RestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DepartureInfoRepository {
    private val webservice: RestApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.mvg.de/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RestApi::class.java)
    }

    private var client: RestApi = webservice

    fun getDepartures(): LiveData<List<DepartureInfo>> {
        val liveData = MutableLiveData<List<DepartureInfo>>()

        client.getDepartureInfoList().enqueue(object: Callback<List<DepartureInfo>> {
            override fun onResponse(call: Call<List<DepartureInfo>>, response: Response<List<DepartureInfo>>) {
                if (response.isSuccessful) {

                    // When data is available, populate LiveData
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<DepartureInfo>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        // Synchronously return LiveData
        // Its value will be available onResponse
        return liveData
    }
}