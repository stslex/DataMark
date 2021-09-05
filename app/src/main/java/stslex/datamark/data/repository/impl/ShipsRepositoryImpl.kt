package stslex.datamark.data.repository.impl

import stslex.datamark.data.repository.interf.ShipsRepository
import stslex.datamark.data.service.ShipsService
import javax.inject.Inject

class ShipsRepositoryImpl @Inject constructor(
    private val service: ShipsService
) : ShipsRepository {

}