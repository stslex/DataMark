package stslex.datamark.data.core

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface DataResponse {

    fun <T> request(response: Response<T>): Flow<DataResult<T>>

    class Base @Inject constructor() : DataResponse {

        override fun <T> request(
            response: Response<T>
        ): Flow<DataResult<T>> = callbackFlow {
            response.responseCallBack { trySendBlocking(it) }
            awaitClose { }
        }

        private inline fun <T> Response<T>.responseCallBack(
            function: (DataResult<T>) -> Unit
        ) = try {
            if (isSuccessful) {
                function(DataResult.Success(body() as T))
            } else {
                function(DataResult.Error(message()))
            }
            Log.i("sdsadad", body()!!.toString())
        } catch (exception: Exception) {
            function(DataResult.Failure(exception))
        }
    }
}