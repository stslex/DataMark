package stslex.datamark.data.model.data

import com.google.gson.annotations.SerializedName

data class ShipsListDataItem(
    @SerializedName("real_count") val real_count: String? = "", //Количество отправленых СИ
    @SerializedName("agg_count") val agg_count: String? = "", //Количество отправленых кодов агрегирования
    @SerializedName("received_count") val received_count: String? = "", //Количество оприходованых кодов
    @SerializedName("taked_at") val taked_at: String? = "", //Дата оприходования
    @SerializedName("code") val code: String? = "" //Код документа по которому отправлены СИ в системе
)
