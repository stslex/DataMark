package stslex.datamark.data.auth

import stslex.datamark.core.Mapper
import stslex.datamark.data.model.ErrorModel
import stslex.datamark.data.model.data.TokenDataModel
import stslex.datamark.data.model.ui.TokenModel
import stslex.datamark.ui.core.UIResult
import javax.inject.Inject

class TokenMapper @Inject constructor() : Mapper.DataToUI<TokenDataModel, UIResult<TokenModel>> {

    override fun map(data: TokenDataModel): UIResult<TokenModel> =
        UIResult.Success(TokenModel(token = data.token))

    override fun map(exception: Exception): UIResult<TokenModel> =
        UIResult.Failure(exception)

    override fun mapError(data: TokenDataModel): UIResult<TokenModel> =
        UIResult.Error(ErrorModel(error = data.error, message = data.message))
}
