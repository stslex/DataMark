package stslex.datamark.data.model.ships_take

import stslex.datamark.data.core.FailureModel

fun ShipsTakeModel.mapSuccess(dataModel: ShipsTakeModel): ShipsTakeModel =
    ShipsTakeModel(
        count = count,
        ships_list = ships_list
    )

fun ShipsTakeModel.mapFailure(dataModel: ShipsTakeModel): FailureModel =
    FailureModel(
        error = error,
        message = message
    )