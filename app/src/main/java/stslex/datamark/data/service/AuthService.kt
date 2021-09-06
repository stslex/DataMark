package stslex.datamark.data.service

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import stslex.datamark.data.model.TokenModel

interface AuthService {
    @POST("/auth/")
    suspend fun authUser(
        @Body requestBody: RequestBody
    ): Response<TokenModel>
}