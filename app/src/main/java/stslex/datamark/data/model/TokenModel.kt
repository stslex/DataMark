package stslex.datamark.data.model

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("token")
    val token: String
)