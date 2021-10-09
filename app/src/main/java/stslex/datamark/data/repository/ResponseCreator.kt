package stslex.datamark.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.HttpException
import retrofit2.Response
import stslex.datamark.data.core.Result
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface ResponseCreator {

    fun <T> request(response: Response<T>): Flow<Result<T>>

    class Base @Inject constructor() : ResponseCreator {

        override fun <T> request(response: Response<T>): Flow<Result<T>> = callbackFlow {
            response.responseCallBack { trySendBlocking(it) }
            awaitClose { }
        }

        private inline fun <T> Response<T>.responseCallBack(
            function: (Result<T>) -> Unit
        ) = try {
            if (isSuccessful) {
                val result = body() as T
                function(Result.Success(result))
            } else {
                function(Result.Failure(HttpException(this)))
            }
        } catch (exception: Exception) {
            function(Result.Failure(exception))
        }
    }
}