package stslex.datamark.data.model.ui

data class ShipsListItem(
    val real_count: String, //Количество отправленых СИ
    val agg_count: String, //Количество отправленых кодов агрегирования
    val received_count: String, //Количество оприходованых кодов
    val taked_at: String, //Дата оприходования
    val code: String //Код документа по которому отправлены СИ в системе
)
