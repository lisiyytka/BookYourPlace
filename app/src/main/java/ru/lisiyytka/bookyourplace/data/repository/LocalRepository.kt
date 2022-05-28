package ru.lisiyytka.bookyourplace.data.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.lisiyytka.bookyourplace.utils.Constants.DATABASE_NAME
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

class LocalRepository @Inject constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUsers = "CREATE TABLE " + TABLE_NAME_USERS + " (" +
            USERS_ID + " VARCHAR(256), " +
            USERS_NAME + " VARCHAR(256), " +
            USERS_BOOKING_ID + " VARCHAR(256))"
        db?.execSQL(createTableUsers)

        val createTableTimeOfReserve = "CREATE TABLE " + TABLE_NAME_TIME_OF_RESERVE + " (" +
            TIME_OF_RESERVE_BOOKING_ID + " VARCHAR(256), " +
            TIME_OF_RESERVE_PLACE_ID + " VARCHAR(256), " +
            TIME_OF_RESERVE_WHO_BOOKED_ID + " VARCHAR(256), " +
            TIME_OF_RESERVE_START_TIME + " VARCHAR(256), " +
            TIME_OF_RESERVE_END_TIME + " VARCHAR(256), " +
            TIME_OF_RESERVE_COUNT_OF_BOOKED_PEOPLE + " VARCHAR(256))"
        db?.execSQL(createTableTimeOfReserve)

        val createTableConferences = "CREATE TABLE " +
            TABLE_NAME_PLACE + " (" +
            PLACE_ID + " VARCHAR(256), " +
            PLACE_NAME + " VARCHAR(256), " +
            PLACE_ADDRESS + " VARCHAR(256), " +
            PLACE_PHONE_NUMBER + " VARCHAR(256))"
        db?.execSQL(createTableConferences)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}