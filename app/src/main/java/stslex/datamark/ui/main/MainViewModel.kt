package stslex.datamark.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import stslex.datamark.data.model.ShipLabelModel
import stslex.datamark.data.model.ShipListModel
import stslex.datamark.data.repository.interf.ShipsRepository
import stslex.datamark.util.Result
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val repository: ShipsRepository
) : ViewModel() {

    suspend fun getShipsList(
        token: String,
        date_from: String,
        date_to: String, 
        page: String
    ): StateFlow<Result<ShipListModel>> =
        repository.getShipsList(token, date_from, date_to, page).stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Result.Loading
        )

    suspend fun getShipsLabels(
        token: String,
        code: String
    ): StateFlow<Result<ShipLabelModel>> =
        repository.getShipsLabel(token, code).stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Result.Loading
        )

    suspend fun makeShips(token: String, code: String, label: List<String>) =
        repository.makeShips(token, code, label).stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Result.Loading
        )

    fun logOut(token: String) = viewModelScope.launch {
        repository.logOut(token)
    }

}