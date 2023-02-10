package com.dobrynland.mvginfowidget.data

import com.dobrynland.mvginfowidget.model.DepartureInfo
import com.dobrynland.mvginfowidget.network.MvgApiService

interface DepartureInfoRepository {
    /** fetches departures info from MVG API **/
    suspend fun getDepartures(): DepartureInfo
}

/**
 * Network Implementation of Repository that fetch departuer info list from MVG API.
 */
class NetworkDepartureInfoRepository(
    private val mvgApiService: MvgApiService
) : DepartureInfoRepository {
    /** Fetches list of departures from marsApi*/
    override suspend fun getDepartures(): DepartureInfo = mvgApiService.getDepartureInfo()
}