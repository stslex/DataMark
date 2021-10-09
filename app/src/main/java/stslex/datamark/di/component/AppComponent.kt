package stslex.datamark.di.component

import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.di.module.*
import stslex.datamark.ui.BaseFragment

@ExperimentalCoroutinesApi
@Component(
    modules = [
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        RetrofitServiceModule::class,
        ResponseModule::class,
        MapperModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: BaseFragment)
}