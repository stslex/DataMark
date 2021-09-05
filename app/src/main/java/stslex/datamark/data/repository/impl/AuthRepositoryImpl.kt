package stslex.datamark.data.repository.impl

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import stslex.datamark.data.model.TokenModel
import stslex.datamark.data.repository.interf.AuthRepository
import stslex.datamark.data.service.AuthService
import stslex.datamark.util.Result
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: AuthService
) : AuthRepository {
    override suspend fun auth(
        username: String,
        password: String,
        agree: Boolean
    ): Flow<Result<TokenModel>> = callbackFlow {
        val response = service.authUser(username, password, agree)
        try {
            if (response.isSuccessful && response.body() != null) {
                val result = response.body() as TokenModel
                trySendBlocking(Result.Success(result))
            }
        } catch (exception: Exception) {
            trySendBlocking(Result.Failure(exception))
        }
        awaitClose { }
    }
}