package stslex.datamark.data.core

import stslex.datamark.core.Mapper

sealed interface DataResult<D> {

    fun <U> map(mapper: Mapper.DataToUI<D, U>): U

    class Success<T>(val data: T) : DataResult<T> {
        override fun <U> map(mapper: Mapper.DataToUI<T, U>): U = mapper.map(data)
    }

    class Error<T>(val data: T) : DataResult<T> {
        override fun <U> map(mapper: Mapper.DataToUI<T, U>): U = mapper.mapError(data)
    }

    class Failure<T>(val exception: Exception) : DataResult<T> {
        override fun <U> map(mapper: Mapper.DataToUI<T, U>): U = mapper.map(exception)
    }
}