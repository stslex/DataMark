package stslex.datamark.util

import android.content.Context
import stslex.datamark.DMApplication
import stslex.datamark.di.component.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is DMApplication -> appComponent
        else -> this.applicationContext.appComponent
    }