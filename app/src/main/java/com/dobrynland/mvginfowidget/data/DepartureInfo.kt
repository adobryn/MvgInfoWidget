package com.dobrynland.mvginfowidget.data

import com.google.gson.annotations.SerializedName

data class DepartureInfo (
    @SerializedName("departure_time") val departureTime: Int?,
    @SerializedName("product") val product: String?,
    @SerializedName("label") val label: String?,
    @SerializedName("destination") val destination: String?,
    @SerializedName("delay") val delay: Boolean?,
    @SerializedName("cancelled") val cancelled: Boolean?
)