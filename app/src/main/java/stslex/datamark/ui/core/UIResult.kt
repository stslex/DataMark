package stslex.datamark.ui.core

sealed class UIResult<out R> {

    data class Success<out T>(val data: T) : UIResult<T>()
    class Error<out T>(val message: String) : UIResult<T>()
    class Failure(val exception: Exception) : UIResult<Nothing>()
    object Loading : UIResult<Nothing>()
}