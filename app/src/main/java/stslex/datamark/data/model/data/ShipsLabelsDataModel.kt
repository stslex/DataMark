package stslex.datamark.data.model.data

import com.google.gson.annotations.SerializedName

data class ShipsLabelsDataModel(
    @SerializedName("labels") val labels: List<LabelDataModel>
) {
    data class LabelDataModel(
        @SerializedName("label") val label: String? = ""
    )
}