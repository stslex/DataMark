package stslex.datamark.data.ships

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import stslex.datamark.data.core.DataResponse
import stslex.datamark.data.core.DataResult
import stslex.datamark.data.model.data.ShipsLabelsDataModel
import stslex.datamark.data.model.data.ShipsListDataModel
import stslex.datamark.data.model.data.ShipsTakeDataModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface ShipsRepository {

    suspend fun getShipsList(
        token: String,
        date_from: String,
        date_to: String,
        page: String
    ): Flow<DataResult<ShipsListDataModel>>

    suspend fun getShipsLabels(token: String, code: String): Flow<DataResult<ShipsLabelsDataModel>>

    suspend fun takeShips(
        token: String,
        code: String,
        labels: List<String>
    ): Flow<DataResult<ShipsTakeDataModel>>

    suspend fun logOut(token: String): Flow<DataResult<String>>

    class Base @Inject constructor(
        private val service: ShipsService,
        private val creator: DataResponse
    ) : ShipsRepository {

        override suspend fun getShipsList(
            token: String,
            date_from: String,
            date_to: String,
            page: String
        ): Flow<DataResult<ShipsListDataModel>> =
            creator.request(service.shipsList(token, date_from, date_to, page))

        override suspend fun getShipsLabels(
            token: String,
            code: String
        ): Flow<DataResult<ShipsLabelsDataModel>> =
            creator.request(service.shipsLabels(token, code))

        override suspend fun logOut(token: String): Flow<DataResult<String>> =
            creator.request(service.logOut(token))

        override suspend fun takeShips(
            token: String,
            code: String,
            labels: List<String>
        ): Flow<DataResult<ShipsTakeDataModel>> =
            creator.request(service.takeShips(token, code, labels))
    }
}