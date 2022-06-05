package ru.lisiyytka.bookyourplace.domain.modelsForFirebase

import ru.lisiyytka.bookyourplace.utils.Constants.STRING_EMPTY

data class UserFirebaseEntity(
    val id: String = STRING_EMPTY,
    val phoneNumber: String = STRING_EMPTY,
    val name: String = STRING_EMPTY,
    val surname: String = STRING_EMPTY,
    val type: String = STRING_EMPTY
)
