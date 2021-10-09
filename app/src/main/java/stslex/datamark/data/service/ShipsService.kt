package stslex.datamark.data.service

import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import stslex.datamark.data.model.ShipsSuccessModel
import stslex.datamark.data.model.ships_take.ShipsTakeModel

interface ShipsService {

    /*Оприходование*/
    @POST("ships/take")
    suspend fun makeShips(
        @Header("Token") token: String,
        @Query("code") code: String,
        @Query("labels") label: List<String>
    ): Response<ShipsSuccessModel>

    /*Список СИ для оприходования*/
    @POST("ships/list")
    suspend fun shipsList(
        @Header("Token") token: String,
        @Query("date_from") date_from: String, //YYYY-MM-DD
        @Query("date_to") date_to: String, //YYYY-MM-DD
        @Query("page") page: String,
    ): Response<ShipsTakeModel>

    @POST("/logout")
    suspend fun logOut(
        @Header("Token") token: String
    ): Response<String>
}