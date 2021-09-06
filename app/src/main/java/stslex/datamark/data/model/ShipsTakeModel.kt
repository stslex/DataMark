package stslex.datamark.data.model

import com.google.gson.annotations.SerializedName

data class ShipsTakeModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("count")
    val count: String
)