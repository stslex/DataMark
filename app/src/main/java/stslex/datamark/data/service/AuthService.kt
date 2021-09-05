package stslex.datamark.data.service

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import stslex.datamark.data.model.TokenModel
import stslex.datamark.util.POST_AUTH
import stslex.datamark.util.QUERY_PASSWORD
import stslex.datamark.util.QUERY_RULES_AGREE
import stslex.datamark.util.QUERY_USERNAME

interface AuthService {

    @POST("/$POST_AUTH/")
    suspend fun authUser(
        @Query(QUERY_USERNAME) username: String,
        @Query(QUERY_PASSWORD) password: String,
        @Query(QUERY_RULES_AGREE) rules_agree: Boolean
    ): Response<TokenModel>
}