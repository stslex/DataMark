package stslex.datamark.data.service

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import stslex.datamark.data.model.ShipLabelModel
import stslex.datamark.data.model.ShipListModel
import stslex.datamark.data.model.ShipsTakeModel
import stslex.datamark.util.*

interface ShipsService {

    @GET("/$GET_CHIPS/")
    suspend fun getShipsList(
        @Header("token") token: String,
        @Query(QUERY_DATE_FROM) date_from: String,
        @Query(QUERY_DATE_TO) date_to: String,
        @Query(QUERY_PAGE) page: String,
    ): Response<ShipListModel>

    @FormUrlEncoded
    @POST("/$POST_SHIPS_LABELS/")
    suspend fun getShipsLabels(
        @Header("token") token: String,
        @Field(QUERY_CODE) code: String
    ): Response<ShipLabelModel>


    @FormUrlEncoded
    @POST("/$POST_SHIPS/")
    suspend fun makeShips(
        @Header("token") token: String,
        @Field(QUERY_CODE) code: String,
        @Field(QUERY_LABEL) label: List<String>
    ): Response<ShipsTakeModel>

    @POST("/logout")
    suspend fun logOut(
        @Header("token") token: String
    ): Response<String>
}