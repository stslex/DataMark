package stslex.datamark.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import stslex.datamark.data.model.ShipListModel
import stslex.datamark.data.model.ShipLabelModel
import stslex.datamark.data.model.ShipsTakeModel
import stslex.datamark.util.*

interface ShipsService {

    @GET("/$GET_CHIPS/")
    fun getShipsList(
        @Query(QUERY_TOKEN) token: String
    ): Response<ShipListModel>

    @POST("/$POST_SHIPS_LABELS/")
    fun getShipsLabels(
        @Query(QUERY_TOKEN) token: String,
        @Query(QUERY_CODE) code: String
    ): Response<ShipLabelModel>

    @POST("/$POST_SHIPS/")
    fun makeShips(
        @Query(QUERY_TOKEN) token: String,
        @Query(QUERY_CODE) code: String,
        @Query(QUERY_LABEL) label: List<String>
    ): Response<ShipsTakeModel>
}