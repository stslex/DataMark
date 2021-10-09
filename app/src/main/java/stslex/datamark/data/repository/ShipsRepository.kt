package stslex.datamark.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import stslex.datamark.data.core.Result
import stslex.datamark.data.model.ships_take.ShipsTakeModel
import stslex.datamark.data.service.ShipsService
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface ShipsRepository {

    suspend fun getShipsTake(
        token: String,
        date_from: String,
        date_to: String,
        page: String
    ): Flow<Result<ShipsTakeModel>>

    suspend fun logOut(token: String): Flow<Result<String>>

    class Base @Inject constructor(
        private val service: ShipsService,
        private val creator: ResponseCreator
    ) : ShipsRepository {

        override suspend fun getShipsTake(
            token: String,
            date_from: String,
            date_to: String,
            page: String
        ): Flow<Result<ShipsTakeModel>> =
            creator.request(service.shipsList(token, date_from, date_to, page))

        override suspend fun logOut(token: String): Flow<Result<String>> =
            creator.request(service.logOut(token))

    }
}