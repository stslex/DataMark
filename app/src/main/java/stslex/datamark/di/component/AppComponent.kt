package stslex.datamark.di.component

import dagger.Component
import stslex.datamark.di.module.RepositoryModule
import stslex.datamark.di.module.RetrofitServiceModule
import stslex.datamark.di.module.ViewModelFactoryModule
import stslex.datamark.di.module.ViewModelModule
import stslex.datamark.ui.BaseFragment

@Component(
    modules = [
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        RetrofitServiceModule::class,
    ]
)
interface AppComponent {
    fun inject(fragment: BaseFragment)
}