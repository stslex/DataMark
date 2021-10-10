package stslex.datamark.data.ships

import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import stslex.datamark.data.model.data.ShipsLabelsDataModel
import stslex.datamark.data.model.data.ShipsListDataModel
import stslex.datamark.data.model.data.ShipsTakeDataModel

interface ShipsService {

    /*Оприходование*/
    @POST("ships/take")
    suspend fun takeShips(
        @Header("Token") token: String,
        @Query("code") code: String,
        @Query("labels") label: List<String>
    ): Response<ShipsTakeDataModel>

    /*Список СИ для оприходования*/
    @POST("ships/list")
    suspend fun shipsList(
        @Header("Token") token: String,
        @Query("date_from") date_from: String, //YYYY-MM-DD
        @Query("date_to") date_to: String, //YYYY-MM-DD
        @Query("page") page: String,
    ): Response<ShipsListDataModel>

    /*Список отгруженных СИ*/
    @POST("ships/labels")
    suspend fun shipsLabels(
        @Header("Token") token: String,
        @Query("code") code: String, //ID отгрузки
    ): Response<ShipsLabelsDataModel>

    @POST("/logout")
    suspend fun logOut(
        @Header("Token") token: String
    ): Response<String>
}