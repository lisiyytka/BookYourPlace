package ru.lisiyytka.bookyourplace.domain.modelsForLocalDb

class TimeOfReserveSQLiteEntity {
    var bookingId: String = ""
    var placeId: String = ""
    var whoBookedId: String = ""
    var startTime: String = ""
    var endTime: String = ""
    var countOfBookedPeople: String = ""

    constructor()

    constructor(
        bookingId: String,
        placeId: String,
        whoBookedId: String,
        startTime: String,
        endTime: String,
        countOfBookedPeople: String
    ) {
        this.bookingId = bookingId
        this.placeId = placeId
        this.whoBookedId = whoBookedId
        this.startTime = startTime
        this.endTime = endTime
        this.countOfBookedPeople = countOfBookedPeople
    }
}