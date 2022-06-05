package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

import ru.lisiyytka.bookyourplace.utils.Constants

data class TableFirebaseEntity(
    val id: String = Constants.STRING_EMPTY,
    val nameOfTable: String = Constants.STRING_EMPTY,
    val description: String = Constants.STRING_EMPTY,
    val numbersOfPersonAtTheTable: String = Constants.STRING_EMPTY,
    val imgOfTableUrl: String = Constants.STRING_EMPTY,
    val timeOfReserve: TimeOfReserveFirebaseEntity = TimeOfReserveFirebaseEntity()
)
