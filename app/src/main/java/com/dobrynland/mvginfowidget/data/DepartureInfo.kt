package com.dobrynland.mvginfowidget.data

import com.google.gson.annotations.SerializedName

data class DepartureInfo (
    @SerializedName("departureTime") val departureTime: Long?,
    @SerializedName("product") val product: String?,
    @SerializedName("label") val label: String?,
    @SerializedName("destination") val destination: String?,
    @SerializedName("live") val live: Boolean?,
    @SerializedName("delay") val delay: Boolean?,
    @SerializedName("cancelled") val cancelled: Boolean?,
    @SerializedName("lineBackgroundColor") val lineBackgroundColor: String?,
    @SerializedName("departureId") val departureId: String?,
    @SerializedName("sev") val sev: Boolean?,
    @SerializedName("platform") val platform: String?,
    @SerializedName("stopPositionNumber") val stopPositionNumber: Int?,
    @SerializedName("infoMessages") val infoMessages: List<String>?,
    @SerializedName("departureTimeMinutes") val departureTimeMinutes: Int?
)