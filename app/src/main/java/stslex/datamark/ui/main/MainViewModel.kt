package stslex.datamark.ui.main

import androidx.lifecycle.ViewModel
import stslex.datamark.data.repository.interf.ShipsRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: ShipsRepository
) : ViewModel() {
}