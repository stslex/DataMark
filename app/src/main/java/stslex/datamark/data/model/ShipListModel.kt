package stslex.datamark.data.model

import com.google.gson.annotations.SerializedName

data class ShipListModel(
    @SerializedName("ships_list")
    val ships_list: List<CodeModel>
)