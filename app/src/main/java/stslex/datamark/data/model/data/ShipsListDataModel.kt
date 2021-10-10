package stslex.datamark.data.model.data

import com.google.gson.annotations.SerializedName

data class ShipsListDataModel(
    @SerializedName("count") val count: String? = "", //Общее количество отгрузок с текущими фильтрами
    @SerializedName("ships_list") val ships_list: List<ShipsListDataItem?>?, //Список
)