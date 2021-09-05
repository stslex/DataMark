package stslex.datamark.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stslex.datamark.data.repository.interf.ShipsRepository
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val repository: ShipsRepository
) : ViewModel() {
}