package stslex.datamark.data.auth

import stslex.datamark.core.Mapper
import stslex.datamark.data.model.data.TokenDataModel
import stslex.datamark.data.model.ui.TokenModel
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

class TokenMapper @Inject constructor() : Mapper.DataToUI<TokenDataModel, UIResult<TokenModel>> {

    override fun map(data: TokenDataModel): UIResult<TokenModel> =
        UIResult.Success(TokenModel(token = data.token.toString()))

    override fun map(exception: Exception): UIResult<TokenModel> =
        UIResult.Failure(exception = exception)

    override fun map(message: String): UIResult<TokenModel> =
        UIResult.Error(message = message)
}
