package stslex.datamark.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import stslex.datamark.data.core.Result
import stslex.datamark.data.model.ships_take.ShipsTakeModel
import stslex.datamark.data.repository.ShipsRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val repository: ShipsRepository
) : ViewModel() {

    suspend fun getShipsTake(
        token: String,
        date_from: String,
        date_to: String,
        page: String
    ): StateFlow<Result<ShipsTakeModel>> =
        repository.getShipsTake(token, date_from, date_to, page).stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Result.Loading
        )

    suspend fun logOut(token: String): StateFlow<Result<String>> =
        repository.logOut(token).stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Result.Loading
        )

}