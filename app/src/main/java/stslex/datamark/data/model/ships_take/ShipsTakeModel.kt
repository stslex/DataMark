package stslex.datamark.data.model.ships_take

import com.google.gson.annotations.SerializedName

data class ShipsTakeModel(
    @SerializedName("count") val count: String? = "", //Общее количество отгрузок с текущими фильтрами
    @SerializedName("ships_list") val ships_list: List<ShipsListItem>, //Список
    @SerializedName("error") val error: String? = "",
    @SerializedName("message") val message: String? = "",
    @SerializedName("details") val details: List<String>? = null
)