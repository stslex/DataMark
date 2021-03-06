package stslex.datamark.util

import android.content.ClipData
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.DMApplication
import stslex.datamark.R
import stslex.datamark.di.component.AppComponent
import stslex.datamark.util.Resources.clipboardManager
import stslex.datamark.util.Resources.labelCopy

@ExperimentalCoroutinesApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is DMApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

fun View.snackBarError(message: String) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackBar.setBackgroundTint(resources.getColor(R.color.error))
    snackBar.setAction(labelCopy) {
        val clip: ClipData = ClipData.newPlainText(labelCopy, message)
        clipboardManager.setPrimaryClip(clip)
    }
    snackBar.show()
}

fun View.snackBarSuccess() {
    val snackBar = Snackbar.make(this, "Success", Snackbar.LENGTH_SHORT)
    snackBar.setAction("OK") {}
    snackBar.show()
}