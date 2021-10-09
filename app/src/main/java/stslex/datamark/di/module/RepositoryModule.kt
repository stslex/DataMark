package stslex.datamark.di.module

import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.data.repository.AuthRepository
import stslex.datamark.data.repository.ShipsRepository

@ExperimentalCoroutinesApi
@Module
interface RepositoryModule {
    @Binds
    fun bindAuthRepository(repository: AuthRepository.Base): AuthRepository

    @Binds
    fun bindShipRepository(repository: ShipsRepository.Base): ShipsRepository
}