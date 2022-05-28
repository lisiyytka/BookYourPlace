package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

data class TableFirebaseEntity(
    val id: String,
    val nameOfTable: String,
    val description: String,
    val numbersOfPersonAtTheTable: String,
    val imgOfTableUrl: String,
    val timeOfReserve: TimeOfReserveFirebaseEntity
    )
