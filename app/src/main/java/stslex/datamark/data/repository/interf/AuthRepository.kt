package stslex.datamark.data.repository.interf

import kotlinx.coroutines.flow.Flow
import stslex.datamark.data.model.TokenModel
import stslex.datamark.util.Result

interface AuthRepository {
    suspend fun auth(username: String, password: String, agree: Boolean): Flow<Result<TokenModel>>
}