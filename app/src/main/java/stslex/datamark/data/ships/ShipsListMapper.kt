package stslex.datamark.data.ships

import stslex.datamark.core.Mapper
import stslex.datamark.data.model.data.ShipsListDataModel
import stslex.datamark.data.model.ui.ShipsListItem
import stslex.datamark.data.model.ui.ShipsListModel
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

class ShipsListMapper @Inject constructor() :
    Mapper.DataToUI<ShipsListDataModel, UIResult<ShipsListModel>> {

    override fun map(data: ShipsListDataModel): UIResult<ShipsListModel> =
        UIResult.Success(
            ShipsListModel(
                count = data.count,
                ships_list = data.ships_list!!.map {
                    ShipsListItem(
                        real_count = it?.real_count.toString(),
                        agg_count = it?.agg_count.toString(),
                        received_count = it?.received_count.toString(),
                        taked_at = it?.taked_at.toString(),
                        code = it?.code.toString()
                    )
                }
            )
        )

    override fun map(exception: Exception): UIResult<ShipsListModel> =
        UIResult.Failure(exception = exception)

    override fun map(message: String): UIResult<ShipsListModel> =
        UIResult.Error(message = message)

}