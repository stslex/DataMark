package stslex.datamark.data.repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import stslex.datamark.data.model.ShipLabelModel
import stslex.datamark.data.model.ShipListModel
import stslex.datamark.data.repository.interf.ShipsRepository
import stslex.datamark.data.service.ShipsService
import stslex.datamark.util.Result
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ShipsRepositoryImpl @Inject constructor(
    private val service: ShipsService
) : ShipsRepository {


    override suspend fun getShipsList(
        token: String,
        date_from: String,
        date_to: String,
        page: String
    ): Flow<Result<ShipListModel>> = callbackFlow {
        try {

            val response = service.getShipsList(token = token, date_from = date_from, date_to = date_to, page = page)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    trySendBlocking(Result.Success(it))
                }
            }
        } catch (exception: Exception) {
            trySendBlocking(Result.Failure(exception))
        }
        awaitClose { }
    }

    override suspend fun getShipsLabel(
        token: String,
        code: String
    ): Flow<Result<ShipLabelModel>> = callbackFlow {
        try {
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token", token)
                .build()
            val response = service.getShipsLabels(requestBody, code)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    trySendBlocking(Result.Success(it))
                }
            }
        } catch (exception: Exception) {
            trySendBlocking(Result.Failure(exception))
        }
        awaitClose { }
    }

    override suspend fun makeShips(token: String, code: String, label: List<String>) =
        callbackFlow {
            try {
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("token", token)
                    .build()
                val response = service.makeShips(requestBody, code, label)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        trySendBlocking(Result.Success(it))
                    }
                }
            } catch (exception: Exception) {
                trySendBlocking(Result.Failure(exception))
            }
            awaitClose { }
        }

    override suspend fun logOut(token: String): Unit = withContext(Dispatchers.IO) {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("token", token)
            .build()
        val response = service.logOut(requestBody)
        if (response.isSuccessful) {
            Log.i("ShipsLogout", response.toString())
        }
    }

}