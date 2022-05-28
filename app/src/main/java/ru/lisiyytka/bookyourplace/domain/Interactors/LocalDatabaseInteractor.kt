package ru.lisiyytka.bookyourplace.domain.Interactors

import android.annotation.SuppressLint
import android.content.ContentValues
import ru.lisiyytka.bookyourplace.data.repository.*
import ru.lisiyytka.bookyourplace.domain.modelsForLocalDb.PlaceSQLiteEntity
import ru.lisiyytka.bookyourplace.domain.modelsForLocalDb.TimeOfReserveSQLiteEntity
import ru.lisiyytka.bookyourplace.domain.modelsForLocalDb.UserSQLiteEntity
import ru.lisiyytka.bookyourplace.utils.Constants.PLACE_ADDRESS
import ru.lisiyytka.bookyourplace.utils.Constants.PLACE_ID
import ru.lisiyytka.bookyourplace.utils.Constants.PLACE_NAME
import ru.lisiyytka.bookyourplace.utils.Constants.PLACE_PHONE_NUMBER
import ru.lisiyytka.bookyourplace.utils.Constants.TABLE_NAME_PLACE
import ru.lisiyytka.bookyourplace.utils.Constants.TABLE_NAME_TIME_OF_RESERVE
import ru.lisiyytka.bookyourplace.utils.Constants.TABLE_NAME_USERS
import ru.lisiyytka.bookyourplace.utils.Constants.TIME_OF_RESERVE_BOOKING_ID
import ru.lisiyytka.bookyourplace.utils.Constants.TIME_OF_RESERVE_COUNT_OF_BOOKED_PEOPLE
import ru.lisiyytka.bookyourplace.utils.Constants.TIME_OF_RESERVE_END_TIME
import ru.lisiyytka.bookyourplace.utils.Constants.TIME_OF_RESERVE_PLACE_ID
import ru.lisiyytka.bookyourplace.utils.Constants.TIME_OF_RESERVE_START_TIME
import ru.lisiyytka.bookyourplace.utils.Constants.TIME_OF_RESERVE_WHO_BOOKED_ID
import ru.lisiyytka.bookyourplace.utils.Constants.USERS_BOOKING_ID
import ru.lisiyytka.bookyourplace.utils.Constants.USERS_ID
import ru.lisiyytka.bookyourplace.utils.Constants.USERS_NAME
import javax.inject.Inject

class LocalDatabaseInteractor @Inject constructor(private val localRepository: LocalRepository) {

    fun insertUser(userEntity: UserSQLiteEntity) {
        val db = localRepository.writableDatabase
        val cv = ContentValues()
        cv.put(USERS_ID, userEntity.id)
        cv.put(USERS_NAME, userEntity.name)
        cv.put(USERS_BOOKING_ID, userEntity.usersBookingId)
        db.insert(TABLE_NAME_USERS, null, cv)
    }

    fun insertTimeOfReserve(timeOfReserveEntity: TimeOfReserveSQLiteEntity) {
        val db = localRepository.writableDatabase
        val cv = ContentValues()
        cv.put(TIME_OF_RESERVE_BOOKING_ID, timeOfReserveEntity.bookingId)
        cv.put(TIME_OF_RESERVE_PLACE_ID, timeOfReserveEntity.placeId)
        cv.put(TIME_OF_RESERVE_WHO_BOOKED_ID, timeOfReserveEntity.whoBookedId)
        cv.put(TIME_OF_RESERVE_START_TIME, timeOfReserveEntity.startTime)
        cv.put(TIME_OF_RESERVE_END_TIME, timeOfReserveEntity.endTime)
        cv.put(TIME_OF_RESERVE_COUNT_OF_BOOKED_PEOPLE, timeOfReserveEntity.countOfBookedPeople)
        db.insert(TABLE_NAME_USERS, null, cv)
    }

    fun insertPlace(placeEntity: PlaceSQLiteEntity) {
        val db = localRepository.writableDatabase
        val cv = ContentValues()
        cv.put(PLACE_ID, placeEntity.id)
        cv.put(PLACE_NAME, placeEntity.name)
        cv.put(PLACE_ADDRESS, placeEntity.address)
        cv.put(PLACE_PHONE_NUMBER, placeEntity.phoneNumber)
        db.insert(TABLE_NAME_PLACE, null, cv)
    }

    @SuppressLint("Range")
    fun getUser(): UserSQLiteEntity {
        val user = UserSQLiteEntity()
        val db = localRepository.readableDatabase
        val query = "Select * from $TABLE_NAME_USERS"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                user.id = result.getString(result.getColumnIndex(USERS_ID)).toString()
                user.name = result.getString(result.getColumnIndex(USERS_NAME)).toString()
                user.usersBookingId = result.getString(result.getColumnIndex(USERS_BOOKING_ID)).toString()
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return user
    }

    @SuppressLint("Range")
    fun getTimeOfReserve(id: String): TimeOfReserveSQLiteEntity {
        val timeOfReserveEntity = TimeOfReserveSQLiteEntity()
        val db = localRepository.readableDatabase
        val query =
            "SELECT * FROM $TABLE_NAME_TIME_OF_RESERVE WHERE $TIME_OF_RESERVE_WHO_BOOKED_ID = ?"
        val value = arrayOf(id)
        val result = db.rawQuery(query, value)
        if (result.moveToFirst()) {
            do {
                timeOfReserveEntity.bookingId =
                    result.getString(result.getColumnIndex(TIME_OF_RESERVE_BOOKING_ID)).toString()
                timeOfReserveEntity.placeId =
                    result.getString(result.getColumnIndex(TIME_OF_RESERVE_PLACE_ID)).toString()
                timeOfReserveEntity.whoBookedId =
                    result.getString(result.getColumnIndex(TIME_OF_RESERVE_WHO_BOOKED_ID)).toString()
                timeOfReserveEntity.startTime =
                    result.getString(result.getColumnIndex(TIME_OF_RESERVE_START_TIME)).toString()
                timeOfReserveEntity.endTime =
                    result.getString(result.getColumnIndex(TIME_OF_RESERVE_END_TIME)).toString()
                timeOfReserveEntity.countOfBookedPeople =
                    result.getString(result.getColumnIndex(TIME_OF_RESERVE_COUNT_OF_BOOKED_PEOPLE)).toString()
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return timeOfReserveEntity
    }

    @SuppressLint("Range")
    fun getPlace(id: String): PlaceSQLiteEntity {
        val place = PlaceSQLiteEntity()
        val db = localRepository.readableDatabase
        val query =
            "SELECT * FROM $TABLE_NAME_PLACE WHERE $TIME_OF_RESERVE_PLACE_ID = ?"
        val value = arrayOf(id)
        val result = db.rawQuery(query, value)
        if (result.moveToFirst()) {
            do {
                place.id = result.getString(result.getColumnIndex(PLACE_ID)).toString()
                place.name = result.getString(result.getColumnIndex(PLACE_NAME)).toString()
                place.address = result.getString(result.getColumnIndex(PLACE_ADDRESS)).toString()
                place.phoneNumber = result.getString(result.getColumnIndex(PLACE_PHONE_NUMBER)).toString()
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return place
    }

    fun deleteUser(id: String) {
        val db = localRepository.writableDatabase
        val value = arrayOf(id)
        db.delete(TABLE_NAME_USERS, "$USERS_ID = ?", value)
        db.close()
    }

    fun deleteTimeOfReserve(id: String) {
        val db = localRepository.writableDatabase
        val value = arrayOf(id)
        db.delete(TABLE_NAME_TIME_OF_RESERVE, "$TIME_OF_RESERVE_WHO_BOOKED_ID = ?", value)
        db.close()
    }

    fun deletePlace(id: String) {
        val db = localRepository.writableDatabase
        val value = arrayOf(id)
        db.delete(TABLE_NAME_PLACE, "$PLACE_ID = ?", value)
        db.close()
    }
}