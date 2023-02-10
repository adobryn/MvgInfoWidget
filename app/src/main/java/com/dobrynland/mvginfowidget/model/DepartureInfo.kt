package com.dobrynland.mvginfowidget.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json     = Json { allowStructuredMapKeys = true }
// val elements = json.parse(Elements.serializer(), jsonString)


@Serializable
data class DepartureInfo(
    val servingLines: List<ServingLine>,
    val departures: List<Departure>
)

@Serializable
data class Departure(
    val departureTime: Long,
    val product: Product,
    val label: String,
    val destination: String,
    val live: Boolean,
    val delay: Long,
    val cancelled: Boolean,
    val lineBackgroundColor: LineBackgroundColor,

    @SerialName("departureId")
    val departureID: String,

    val sev: Boolean,
    val platform: String,
    val stopPositionNumber: Long,
    val infoMessages: List<String>,
    val displayInfoMessage: String? = null
)

@Serializable
enum class LineBackgroundColor(val value: String) {
    @SerialName("#0d5c70")
    The0D5C70("#0d5c70"),

    @SerialName("#942d8d")
    The942D8D("#942d8d");
}

@Serializable
enum class Product(val value: String) {
    @SerialName("REGIONAL_BUS")
    RegionalBus("REGIONAL_BUS"),

    @SerialName("SBAHN")
    Sbahn("SBAHN");
}

@Serializable
data class ServingLine(
    val destination: String,
    val sev: Boolean,
    val network: String,
    val product: Product,
    val lineNumber: String,

    @SerialName("divaId")
    val divaID: String
)
