package stslex.datamark.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import stslex.datamark.ViewModelFactory

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}