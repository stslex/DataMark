package stslex.datamark.di.module

import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.data.repository.ResponseCreator

@ExperimentalCoroutinesApi
@Module
interface ResponseModule {

    @Binds
    fun bindsResponseCreator(response: ResponseCreator.Base): ResponseCreator
}