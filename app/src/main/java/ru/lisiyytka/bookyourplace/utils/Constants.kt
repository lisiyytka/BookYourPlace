package ru.lisiyytka.bookyourplace.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object Constants {
    //For local database
    const val DATABASE_NAME = "LocaleDataBase"
    const val TABLE_NAME_USERS = "Users"
    const val TABLE_NAME_TIME_OF_RESERVE = "LocalRepositories"
    const val TABLE_NAME_PLACE = "LocalRepositories"
    const val USERS_ID = "Id"
    const val USERS_NAME = "UsersName"
    const val USERS_BOOKING_ID = "UsersBookingId"
    const val TIME_OF_RESERVE_BOOKING_ID = "BookingId"
    const val TIME_OF_RESERVE_PLACE_ID = "PlaceId"
    const val TIME_OF_RESERVE_WHO_BOOKED_ID = "WhoBookedId"
    const val TIME_OF_RESERVE_START_TIME = "StartTime"
    const val TIME_OF_RESERVE_END_TIME = "EndTime"
    const val TIME_OF_RESERVE_COUNT_OF_BOOKED_PEOPLE = "CountOfBookedPeople"
    const val PLACE_ID = "Id"
    const val PLACE_NAME = "Name"
    const val PLACE_ADDRESS = "Address"
    const val PLACE_PHONE_NUMBER = "PhoneNumber"
    //const for firebase db
    const val NODE_PLACE = "Place"
    const val NODE_USERS = "Users"
    const val NODE_TABLES = "Tables"
    const val NODE_TIME_OF_RESERVE = "TimeOfReserve"
    const val NODE_FAVORITE_PLACES = "FavoritePlaces"
    const val FOLDER_PLACE_IMAGE = "PlaceImage"
    const val PHOTO_URL = "PhotoUrl"
    //strings
    const val STRING_EMPTY = ""
    //firebase database
    val AUTH: FirebaseAuth = FirebaseAuth.getInstance()
    val REF_DATABASE_ROOT: DatabaseReference =
        FirebaseDatabase.getInstance("https://bookyourplace-b2532-default-rtdb.europe-west1.firebasedatabase.app/").reference
    val REF_STORAGE_ROOT: StorageReference = FirebaseStorage.getInstance("gs://bookyourplace-b2532.appspot.com").reference
    //role
    const val ROLE_USER = "user"
    const val ROLE_OWNER = "owner"
}