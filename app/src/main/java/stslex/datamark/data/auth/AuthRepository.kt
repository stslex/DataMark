package stslex.datamark.data.auth

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import stslex.datamark.data.core.DataResponse
import stslex.datamark.data.core.DataResult
import stslex.datamark.data.model.data.TokenDataModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface AuthRepository {

    suspend fun auth(
        username: String,
        password: String,
        agree: Boolean
    ): Flow<DataResult<TokenDataModel>>

    class Base @Inject constructor(
        private val service: AuthService,
        private val creator: DataResponse
    ) : AuthRepository {

        override suspend fun auth(
            username: String,
            password: String,
            agree: Boolean
        ): Flow<DataResult<TokenDataModel>> =
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