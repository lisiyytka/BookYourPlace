package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

data class UserFirebaseEntity(
    val id: String,
    val phoneNumber: String,
    val name: String,
    val surname: String,
    val favoritePlaces: PlaceFirebaseEntity,
    val usersBooking: TimeOfReserveFirebaseEntity
)
