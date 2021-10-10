package stslex.datamark.ui.core

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import stslex.datamark.core.Mapper
import stslex.datamark.data.core.DataResult
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UIResponse {

    fun <D, U> request(
        flow: Flow<DataResult<D>>,
        mapper: Mapper.DataToUI<D, UIResult<U>>
    ): Flow<UIResult<U>>

    fun request(flow: Flow<DataResult<String>>): Flow<UIResult<String>>

    class Base @Inject constructor() : UIResponse {

        override fun request(flow: Flow<DataResult<String>>): Flow<UIResult<String>> =
            callbackFlow {
                flow.responseCallBack {
                    trySendBlocking(it)
                }
                awaitClose { }
            }

        override fun <D, U> request(
            flow: Flow<DataResult<D>>,
            mapper: Mapper.DataToUI<D, UIResult<U>>
        ): Flow<UIResult<U>> = callbackFlow {
            flow.responseCallBack(mapper) {
                trySendBlocking(it)
            }
            awaitClose { }
        }

        private suspend inline fun Flow<DataResult<String>>.responseCallBack(
            crossinline function: (UIResult<String>) -> Unit
        ) = try {
            collect {
                when (it) {
                    is DataResult.Success -> function(UIResult.Success(it.data))
                    is DataResult.Failure -> function(UIResult.Failure(it.exception))
                    is DataResult.Error -> function(UIResult.Error(it.message))
                }
            }
        } catch (exception: Exception) {
            function(UIResult.Failure(exception))
        }

        private suspend inline fun <D, U> Flow<DataResult<D>>.responseCallBack(
            mapper: Mapper.DataToUI<D, UIResult<U>>,
            crossinline function: (UIResult<U>) -> Unit
        ) = try {
            collect {
                function(it.map(mapper))
            }
        } catch (exception: Exception) {
            function(UIResult.Failure(exception))
        }
    }
}