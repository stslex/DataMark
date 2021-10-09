package stslex.datamark.data.model.data

import com.google.gson.annotations.SerializedName

data class TokenDataModel(
    @SerializedName("token") val token: String,
    @SerializedName("error") val error: String? = "",
    @SerializedName("message") val message: String? = "",
    @SerializedName("details") val details: List<String>? = emptyList()
)