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
    fun getAllEvents(): Observable<DataSnapshot> = Observable.create { subscriber ->
        REF_DATABASE_ROOT.child(NODE_PLACE).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    subscriber.onNext(snapshot)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }


    fun getAllTables(id: String): Observable<DataSnapshot> = Observable.create { subscriber ->
        REF_DATABASE_ROOT.child(id).child(NODE_TABLES).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    subscriber.onNext(snapshot)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    fun getTimeOfReserveForUser(idUser: String): Observable<DataSnapshot> = Observable.create { subscriber ->
        REF_DATABASE_ROOT.child(idUser).child(NODE_TIME_OF_RESERVE).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    subscriber.onNext(snapshot)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    fun getTimeOfReserveForTable(idTable: String): Observable<DataSnapshot> = Observable.create { subscriber ->
        REF_DATABASE_ROOT.child(idTable).child(NODE_TIME_OF_RESERVE).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    subscriber.onNext(snapshot)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    fun saveUser(userFirebaseEntity: UserFirebaseEntity) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(userFirebaseEntity.id).setValue(userFirebaseEntity)
    }

    fun savePlaceInFavorite(idUser: String, placeFirebaseEntity: PlaceFirebaseEntity) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(idUser).child(NODE_FAVORITE_PLACES).child(placeFirebaseEntity.id)
            .setValue(placeFirebaseEntity)
    }

    fun saveTableInPlace(idPlace: String, tableFirebaseEntity: TableFirebaseEntity) {
        REF_DATABASE_ROOT.child(NODE_PLACE).child(idPlace).child(NODE_TABLES).child(tableFirebaseEntity.id)
            .setValue(tableFirebaseEntity)
    }

    fun savePlace(idPlace: String, tableFirebaseEntity: TableFirebaseEntity) {
        REF_DATABASE_ROOT.child(NODE_PLACE).child(idPlace).setValue(tableFirebaseEntity)
    }
}