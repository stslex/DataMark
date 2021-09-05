package stslex.datamark.util

import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.DMApplication
import stslex.datamark.di.component.AppComponent

@ExperimentalCoroutinesApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is DMApplication -> appComponent
        else -> this.applicationContext.appComponent
    }