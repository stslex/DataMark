package stslex.datamark.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import stslex.datamark.core.Mapper
import stslex.datamark.data.model.data.ShipsListDataModel
import stslex.datamark.data.model.ui.ShipsListModel
import stslex.datamark.data.ships.ShipsRepository
import stslex.datamark.ui.core.UIResponse
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val repository: ShipsRepository,
    private val creator: UIResponse,
    private val mapperShipsList: Mapper.DataToUI<ShipsListDataModel, UIResult<ShipsListModel>>,
) : ViewModel() {

    suspend fun getShipsTake(
        token: String,
        date_from: String,
        date_to: String,
        page: String
    ): StateFlow<UIResult<ShipsListModel>> =
        creator.request(repository.getShipsTake(token, date_from, date_to, page), mapperShipsList)
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