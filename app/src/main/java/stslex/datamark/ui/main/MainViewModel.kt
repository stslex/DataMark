package stslex.datamark.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import stslex.datamark.core.Mapper
import stslex.datamark.data.model.data.ShipsLabelsDataModel
import stslex.datamark.data.model.data.ShipsListDataModel
import stslex.datamark.data.model.data.ShipsTakeDataModel
import stslex.datamark.data.model.ui.ShipsLabelsModel
import stslex.datamark.data.model.ui.ShipsListModel
import stslex.datamark.data.model.ui.ShipsTakeModel
import stslex.datamark.data.ships.ShipsRepository
import stslex.datamark.ui.core.UIResponse
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val repository: ShipsRepository,
    private val creator: UIResponse,
    private val mapperShipsList: Mapper.DataToUI<ShipsListDataModel, UIResult<ShipsListModel>>,
    private val mapperShipsLabels: Mapper.DataToUI<ShipsLabelsDataModel, UIResult<ShipsLabelsModel>>,
    private val mapperShipsTake: Mapper.DataToUI<ShipsTakeDataModel, UIResult<ShipsTakeModel>>,
) : ViewModel() {

    suspend fun getShipsList(
        token: String,
        date_from: String,
        date_to: String,
        page: String
    ): StateFlow<UIResult<ShipsListModel>> =
        creator.request(repository.getShipsList(token, date_from, date_to, page), mapperShipsList)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                UIResult.Loading
            )

    suspend fun takeShips(
        token: String,
        code: String,
        labels: List<String>
    ): StateFlow<UIResult<ShipsTakeModel>> =
        creator.request(repository.takeShips(token, code, labels), mapperShipsTake)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                UIResult.Loading
            )

    suspend fun getShipsLabels(
        token: String,
        code: String
    ): StateFlow<UIResult<ShipsLabelsModel>> =
        creator.request(repository.getShipsLabels(token, code), mapperShipsLabels)
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                UIResult.Loading
            )

    suspend fun logOut(token: String): StateFlow<UIResult<String>> =
        creator.request(repository.logOut(token)).stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            UIResult.Loading
        )

}