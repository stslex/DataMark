package stslex.datamark.di.module

import dagger.Binds
import dagger.Module
import stslex.datamark.data.repository.impl.AuthRepositoryImpl
import stslex.datamark.data.repository.impl.ShipsRepositoryImpl
import stslex.datamark.data.repository.interf.AuthRepository
import stslex.datamark.data.repository.interf.ShipsRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindShipRepository(repository: ShipsRepositoryImpl): ShipsRepository
}