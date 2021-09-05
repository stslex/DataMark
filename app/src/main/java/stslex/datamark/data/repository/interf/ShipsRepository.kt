package stslex.datamark.data.repository.interf

import kotlinx.coroutines.flow.Flow
import stslex.datamark.data.model.ShipListModel
import stslex.datamark.data.model.ShipsLabelsListModel
import stslex.datamark.util.Result

interface ShipsRepository {
    suspend fun getShipsList(token: String): Flow<Result<ShipListModel>>
    suspend fun getShipsLabel(token: String, code: String): Flow<Result<ShipsLabelsListModel>>
    suspend fun makeShips(token: String, code: String, label: String)
}