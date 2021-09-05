package stslex.datamark

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.di.component.AppComponent
import stslex.datamark.di.component.DaggerAppComponent

@ExperimentalCoroutinesApi
class DMApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}