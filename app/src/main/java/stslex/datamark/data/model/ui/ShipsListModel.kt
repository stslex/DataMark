package stslex.datamark.data.model.ui

data class ShipsListModel(
    val count: String? = "", //Общее количество отгрузок с текущими фильтрами
    val ships_list: List<ShipsListItem>, //Список
)