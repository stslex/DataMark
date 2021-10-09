package stslex.datamark.data.model

data class ErrorModel(

    val error: String? = "",
    val message: String? = "",
    val details: List<String>? = emptyList()
)