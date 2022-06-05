package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

import kotlinx.serialization.Serializable
import ru.lisiyytka.bookyourplace.utils.Constants.STRING_EMPTY
import javax.xml.xpath.XPathConstants.STRING

data class PlaceFirebaseEntity(
    val id: String = STRING_EMPTY,
    val phoneNumber: String = STRING_EMPTY,
    val nameOfPlace: String = STRING_EMPTY,
    val typeOfPlace:String = STRING_EMPTY,
    val address: String = STRING_EMPTY,
    val phoneNumbersOnProfile: String = STRING_EMPTY,
    val cuisine: String = STRING_EMPTY,
    val schedule: String = STRING_EMPTY,
    val averageCheck: String = STRING_EMPTY,
    val imgOfPlaceUrl: String = STRING_EMPTY
)
