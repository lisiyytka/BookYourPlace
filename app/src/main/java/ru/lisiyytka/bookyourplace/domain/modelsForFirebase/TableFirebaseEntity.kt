package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

import ru.lisiyytka.bookyourplace.utils.Constants

data class TableFirebaseEntity(
    val nameOfTable: String = Constants.STRING_EMPTY,
    val description: String = Constants.STRING_EMPTY,
    val numbersOfPersonAtTheTable: String = Constants.STRING_EMPTY,
    val imgOfTableUrl: String = Constants.STRING_EMPTY
)
