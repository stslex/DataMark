package stslex.datamark.data.repository.interf

import kotlinx.coroutines.flow.Flow
import stslex.datamark.data.model.ShipLabelModel
import stslex.datamark.data.model.ShipListModel
import stslex.datamark.data.model.ShipsTakeModel
import stslex.datamark.util.Result

interface ShipsRepository {

    suspend fun getShipsList(
        token: String,
        date_from: String,
        date_to: String,
        page: String
    ): Flow<Result<ShipListModel>>

    suspend fun getShipsLabel(
        token: String,
        code: String
    ): Flow<Result<ShipLabelModel>>

    suspend fun makeShips(
        token: String,
        code: String,
        label: List<String>
    ): Flow<Result<ShipsTakeModel>>

    suspend fun logOut(token: String)
}