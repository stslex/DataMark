package stslex.datamark.ui.core

import stslex.datamark.data.model.ErrorModel

sealed class UIResult<out R> {

    data class Success<out T>(val data: T) : UIResult<T>()
    class Error<out T>(val error: ErrorModel?) : UIResult<T>()
    class Failure(val exception: Exception) : UIResult<Nothing>()
    object Loading : UIResult<Nothing>()
}