package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

import ru.lisiyytka.bookyourplace.utils.Constants

data class TimeOfReserveFirebaseEntity(
    val id: String = Constants.STRING_EMPTY,
    val startTime: String = Constants.STRING_EMPTY,
    val endTime: String = Constants.STRING_EMPTY,
    val whoBookedId: String = Constants.STRING_EMPTY,
    val countOfBookedPeople: String = Constants.STRING_EMPTY
)
