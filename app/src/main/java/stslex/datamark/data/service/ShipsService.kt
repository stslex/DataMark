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
        @Field("token") token: String,
        @Field(QUERY_DATE_FROM) date_from: String,
        @Field(QUERY_DATE_TO) date_to: String,
        @Field(QUERY_PAGE) page: String
    ): Response<ShipListModel>

    @POST("/$POST_SHIPS_LABELS/")
    suspend fun getShipsLabels(
        @Body requestBody: RequestBody,
        @Query(QUERY_CODE) code: String
    ): Response<ShipLabelModel>

    @POST("/$POST_SHIPS/")
    suspend fun makeShips(
        @Body requestBody: RequestBody,
        @Query(QUERY_CODE) code: String,
        @Query(QUERY_LABEL) label: List<String>
    ): Response<ShipsTakeModel>

    @POST("/logout")
    suspend fun logOut(
        @Body requestBody: RequestBody,
    ): Response<String>
}