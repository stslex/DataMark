package stslex.datamark.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import stslex.datamark.data.core.Result
import stslex.datamark.data.model.TokenModel
import stslex.datamark.data.repository.AuthRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    suspend fun auth(
        username: String,
        password: String,
        agree: Boolean
    ): StateFlow<Result<TokenModel>> =
        repository.auth(username, password, agree)
            .stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)
}