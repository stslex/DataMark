package stslex.datamark.data.model.data

import com.google.gson.annotations.SerializedName
import stslex.datamark.data.model.ui.ShipsListItem

data class ShipsListDataModel(
    @SerializedName("count") val count: String? = "", //Общее количество отгрузок с текущими фильтрами
    @SerializedName("ships_list") val ships_list: List<ShipsListItem>, //Список
    @SerializedName("error") val error: String? = "",
    @SerializedName("message") val message: String? = "",
    @SerializedName("details") val details: List<String>? = emptyList()
)