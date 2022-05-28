package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

data class PlaceFirebaseEntity(
    val id: String,
    val phoneNumber: String,
    val nameOfPlace: String,
    val address: String,
    val phoneNumbersOnProfile: String,
    val cuisine: String,
    val averageCheck: String,
    val imgOfPlaceUrl: String,
    val tables: TableFirebaseEntity
)
