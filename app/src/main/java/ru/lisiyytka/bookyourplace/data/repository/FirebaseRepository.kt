package ru.lisiyytka.bookyourplace.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.PlaceFirebaseEntity
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.TableFirebaseEntity
import ru.lisiyytka.bookyourplace.domain.modelsForFirebase.UserFirebaseEntity
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_FAVORITE_PLACES
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_TABLES
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_TIME_OF_RESERVE
import ru.lisiyytka.bookyourplace.utils.Constants.NODE_USERS
import ru.lisiyytka.bookyourplace.utils.Constants.REF_DATABASE_ROOT
import toothpick.InjectConstructor

@InjectConstructor
class FirebaseRepository {
    fun saveUser(userFirebaseEntity: UserFirebaseEntity) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(userFirebaseEntity.id).setValue(userFirebaseEntity)
    }
}