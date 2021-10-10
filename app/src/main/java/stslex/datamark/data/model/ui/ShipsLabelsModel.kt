package stslex.datamark.data.model.ui

data class ShipsLabelsModel(
    val labels: List<LabelModel>
) {
    data class LabelModel(
        val label: String
    )
}