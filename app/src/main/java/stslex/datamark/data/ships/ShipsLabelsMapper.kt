package stslex.datamark.data.ships

import stslex.datamark.core.Mapper
import stslex.datamark.data.model.data.ShipsLabelsDataModel
import stslex.datamark.data.model.ui.ShipsLabelsModel
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

class ShipsLabelsMapper @Inject constructor() :
    Mapper.DataToUI<ShipsLabelsDataModel, UIResult<ShipsLabelsModel>> {

    override fun map(data: ShipsLabelsDataModel): UIResult<ShipsLabelsModel> =
        UIResult.Success(
            ShipsLabelsModel(
                labels = data.labels.map {
                    ShipsLabelsModel.LabelModel(label = it.label.toString())
                }
            )
        )

    override fun map(exception: Exception): UIResult<ShipsLabelsModel> =
        UIResult.Failure(exception = exception)

    override fun map(message: String): UIResult<ShipsLabelsModel> =
        UIResult.Error(message = message)

}