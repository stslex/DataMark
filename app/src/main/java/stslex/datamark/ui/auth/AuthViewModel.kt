package stslex.datamark.ui.auth

import androidx.lifecycle.ViewModel
import stslex.datamark.data.repository.interf.AuthRepository
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
}