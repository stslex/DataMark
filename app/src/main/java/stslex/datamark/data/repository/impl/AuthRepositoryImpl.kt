package stslex.datamark.data.repository.impl

import stslex.datamark.data.repository.interf.AuthRepository
import stslex.datamark.data.service.AuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: AuthService
) : AuthRepository {

}