package stslex.datamark.data.ships

import stslex.datamark.core.Mapper
import stslex.datamark.data.model.ErrorModel
import stslex.datamark.data.model.data.ShipsListDataModel
import stslex.datamark.data.model.ui.ShipsListModel
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

class ShipsListMapper @Inject constructor() :
    Mapper.DataToUI<ShipsListDataModel, UIResult<ShipsListModel>> {

    override fun map(data: ShipsListDataModel): UIResult<ShipsListModel> =
        UIResult.Success(
            ShipsListModel(
                count = data.count,
                ships_list = data.ships_list
            )
        )

    override fun map(exception: Exception): UIResult<ShipsListModel> =
        UIResult.Failure(exception = exception)

    override fun mapError(data: ShipsListDataModel): UIResult<ShipsListModel> =
        UIResult.Error(
            ErrorModel(
                error = data.error,
                message = data.message,
                details = data.details
            )
        )

}