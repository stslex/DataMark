package stslex.datamark.data.ships

import stslex.datamark.core.Mapper
import stslex.datamark.data.model.data.ShipsTakeDataModel
import stslex.datamark.data.model.ui.ShipsTakeModel
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

class ShipsTakeMapper @Inject constructor() :
    Mapper.DataToUI<ShipsTakeDataModel, UIResult<ShipsTakeModel>> {

    override fun map(data: ShipsTakeDataModel): UIResult<ShipsTakeModel> =
        UIResult.Success(ShipsTakeModel(count = data.count.toString()))

    override fun map(exception: Exception): UIResult<ShipsTakeModel> =
        UIResult.Failure(exception = exception)

    override fun map(message: String): UIResult<ShipsTakeModel> =
        UIResult.Error(message = message)

}