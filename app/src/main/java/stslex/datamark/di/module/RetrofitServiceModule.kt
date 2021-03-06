package stslex.datamark.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import stslex.datamark.data.auth.AuthService
import stslex.datamark.data.ships.ShipsService

@Module(includes = [RetrofitClientModule::class])
class RetrofitServiceModule {

    @Provides
    fun provideRetrofitAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideRetrofitShipService(retrofit: Retrofit): ShipsService =
        retrofit.create(ShipsService::class.java)
}