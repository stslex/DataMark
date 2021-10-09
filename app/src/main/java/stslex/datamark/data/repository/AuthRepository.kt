package stslex.datamark.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import stslex.datamark.data.core.Result
import stslex.datamark.data.model.TokenModel
import stslex.datamark.data.service.AuthService
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface AuthRepository {

    suspend fun auth(username: String, password: String, agree: Boolean): Flow<Result<TokenModel>>

    class Base @Inject constructor(
        private val service: AuthService,
        private val creator: ResponseCreator
    ) : AuthRepository {

        override suspend fun auth(
            username: String,
            password: String,
            agree: Boolean
        ): Flow<Result<TokenModel>> =
            creator.request(service.authUser(requestBody(username, password, agree)))

        private fun requestBody(username: String, password: String, agree: Boolean) =
            MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", username)
                .addFormDataPart("password", password)
                .addFormDataPart("is_rules_agree", agree.toString())
                .build()


    }
}