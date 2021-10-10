package stslex.datamark.di.module

import dagger.Module
import dagger.Provides
import stslex.datamark.core.Mapper
import stslex.datamark.data.auth.TokenMapper
import stslex.datamark.data.model.data.ShipsLabelsDataModel
import stslex.datamark.data.model.data.ShipsListDataModel
import stslex.datamark.data.model.data.ShipsTakeDataModel
import stslex.datamark.data.model.data.TokenDataModel
import stslex.datamark.data.model.ui.ShipsLabelsModel
import stslex.datamark.data.model.ui.ShipsListModel
import stslex.datamark.data.model.ui.ShipsTakeModel
import stslex.datamark.data.model.ui.TokenModel
import stslex.datamark.data.ships.ShipsLabelsMapper
import stslex.datamark.data.ships.ShipsListMapper
import stslex.datamark.data.ships.ShipsTakeMapper
import stslex.datamark.ui.core.UIResult

@Module
class MapperModule {

    @Provides
    fun providesTokenMapper(): Mapper.DataToUI<TokenDataModel, UIResult<TokenModel>> =
        TokenMapper()

    @Provides
    fun providesShipsListMapper(): Mapper.DataToUI<ShipsListDataModel, UIResult<ShipsListModel>> =
        ShipsListMapper()

    @Provides
    fun providesShipsLabelsMapper(): Mapper.DataToUI<ShipsLabelsDataModel, UIResult<ShipsLabelsModel>> =
        ShipsLabelsMapper()

    @Provides
    fun providesShipsTakeMappersMapper(): Mapper.DataToUI<ShipsTakeDataModel, UIResult<ShipsTakeModel>> =
        ShipsTakeMapper()
}