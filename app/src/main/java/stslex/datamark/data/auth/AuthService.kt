package stslex.datamark.data.auth

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import stslex.datamark.data.model.data.TokenDataModel

interface AuthService {

    @POST("/auth/")
    suspend fun authUser(
        @Body requestBody: RequestBody
    ): Response<TokenDataModel>
}