package stslex.datamark.data.model

import com.google.gson.annotations.SerializedName

data class ShipLabelModel(
    @SerializedName("labels")
    val labels: List<LabelModel>
)