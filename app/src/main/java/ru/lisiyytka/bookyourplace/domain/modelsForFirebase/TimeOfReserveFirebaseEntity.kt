package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

data class TimeOfReserveFirebaseEntity(
    val id: String,
    val startTime: String,
    val endTime: String,
    val whoBookedId: String,
    val countOfBookedPeople: String
)
