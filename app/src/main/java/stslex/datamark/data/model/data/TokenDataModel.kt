package stslex.datamark.data.model.data

import com.google.gson.annotations.SerializedName

data class TokenDataModel(
    @SerializedName("token") val token: String,
    @SerializedName("message") val message: String
)

data class Message(
    @SerializedName("message") val message: String
)