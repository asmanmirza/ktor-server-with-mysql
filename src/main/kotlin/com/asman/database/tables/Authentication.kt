package com.asman.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object LoginTable : IntIdTable() {
    val imei: Column<String> = varchar("imei", 50)
    val username: Column<String> = varchar("username", 50).uniqueIndex()
    val password: Column<String> = varchar("password", 50)
    val authToken: Column<String> = varchar("authToken", 50)
}

class LoginEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LoginEntity>(LoginTable)

    var imei by LoginTable.imei
    var username by LoginTable.username
    var password by LoginTable.password
    var authToken by LoginTable.authToken
}