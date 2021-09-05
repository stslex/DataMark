package stslex.datamark.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import stslex.datamark.data.model.TokenModel
import stslex.datamark.data.repository.interf.AuthRepository
import stslex.datamark.util.Result
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    suspend fun auth(
        username: String,
        password: String,
        agree: Boolean
    ): StateFlow<Result<TokenModel>> =
        repository.auth(username, password, agree)
            .stateIn(
                viewModelScope
            )
}