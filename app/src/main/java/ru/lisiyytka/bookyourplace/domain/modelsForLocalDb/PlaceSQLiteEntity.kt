package ru.lisiyytka.bookyourplace.domain.modelsForLocalDb

class PlaceSQLiteEntity {
    var id: String = ""
    var name: String = ""
    var address: String = ""
    var phoneNumber: String = ""

    constructor()

    constructor(id: String, name: String, usersBookingId: String, phoneNumber: String) {
        this.id = id
        this.name = name
        this.address = usersBookingId
        this.phoneNumber = phoneNumber
    }
}