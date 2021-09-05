package stslex.datamark.di.module

import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.data.repository.impl.AuthRepositoryImpl
import stslex.datamark.data.repository.impl.ShipsRepositoryImpl
import stslex.datamark.data.repository.interf.AuthRepository
import stslex.datamark.data.repository.interf.ShipsRepository

@ExperimentalCoroutinesApi
@Module
interface RepositoryModule {
    @Binds
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindShipRepository(repository: ShipsRepositoryImpl): ShipsRepository
}