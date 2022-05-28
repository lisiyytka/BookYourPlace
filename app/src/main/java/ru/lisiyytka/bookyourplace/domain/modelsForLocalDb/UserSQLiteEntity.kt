package ru.lisiyytka.bookyourplace.domain.modelsForLocalDb

class UserSQLiteEntity {
    var id: String = ""
    var name: String = ""
    var usersBookingId: String = ""

    constructor()

    constructor(id: String, name: String, usersBookingId: String) {
        this.id = id
        this.name = name
        this.usersBookingId = usersBookingId
    }
}