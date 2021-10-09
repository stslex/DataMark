package stslex.datamark.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import stslex.datamark.data.auth.AuthRepository
import stslex.datamark.data.auth.TokenMapper
import stslex.datamark.data.model.ui.TokenModel
import stslex.datamark.ui.core.UIResponse
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val mapper: TokenMapper,
    private val creator: UIResponse
) : ViewModel() {

    suspend fun auth(
        username: String,
        password: String,
        agree: Boolean
    ): StateFlow<UIResult<TokenModel>> =
        creator.request(
            flow = repository.auth(username, password, agree),
            mapper = mapper
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

}