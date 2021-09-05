package stslex.datamark.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import stslex.datamark.di.key.ViewModelKey
import stslex.datamark.ui.auth.AuthViewModel
import stslex.datamark.ui.main.MainViewModel

@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}