package stslex.datamark.data.ships

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import stslex.datamark.data.core.DataResponse
import stslex.datamark.data.core.DataResult
import stslex.datamark.data.model.data.ShipsListDataModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface ShipsRepository {

    suspend fun getShipsTake(
        token: String,
        date_from: String,
        date_to: String,
        page: String
    ): Flow<DataResult<ShipsListDataModel>>

    suspend fun logOut(token: String): Flow<DataResult<String>>

    class Base @Inject constructor(
        private val service: ShipsService,
        private val creator: DataResponse
    ) : ShipsRepository {

        override suspend fun getShipsTake(
            token: String,
            date_from: String,
            date_to: String,
            page: String
        ): Flow<DataResult<ShipsListDataModel>> =
            creator.request(service.shipsList(token, date_from, date_to, page))

        override suspend fun logOut(token: String): Flow<DataResult<String>> =
            creator.request(service.logOut(token))

    }
}