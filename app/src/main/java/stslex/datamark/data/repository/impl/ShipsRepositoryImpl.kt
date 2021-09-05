package stslex.datamark.data.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import stslex.datamark.data.model.ShipListModel
import stslex.datamark.data.model.ShipsLabelsListModel
import stslex.datamark.data.repository.interf.ShipsRepository
import stslex.datamark.data.service.ShipsService
import stslex.datamark.util.Result
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ShipsRepositoryImpl @Inject constructor(
    private val service: ShipsService
) : ShipsRepository {
    override suspend fun getShipsList(token: String): Flow<Result<ShipListModel>> = callbackFlow {
        try {
            val response = service.getShipsList(token)
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
    ): Flow<Result<ShipsLabelsListModel>> = callbackFlow {
        try {
            val response = service.getShipsLabels(token, code)
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

    override suspend fun makeShips(token: String, code: String, label: String) =
        withContext(Dispatchers.IO) {
            service.makeShips(token, code, label)
        }

}