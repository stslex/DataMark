package stslex.datamark.data.repository.impl

import stslex.datamark.data.repository.interf.ShipsRepository
import stslex.datamark.data.service.AuthService
import javax.inject.Inject

class ShipsRepositoryImpl @Inject constructor(
    private val service: AuthService
) : ShipsRepository {

}