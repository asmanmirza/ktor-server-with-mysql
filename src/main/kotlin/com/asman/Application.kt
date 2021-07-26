package com.asman

import com.asman.database.SqlDatabase
import com.asman.database.tables.LoginTable
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.asman.plugins.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    runCatching {
        SqlDatabase.initDB()
        transaction {
            SchemaUtils.create(LoginTable)
        }
    }


    embeddedServer(Netty, port = 80, host = "127.0.0.1") {
        configureRouting()
        configureSerialization()
        configureAdministration()
        configureMonitoring()
        configureHTTP()
        configureSecurity()
    }.start(wait = true)
}
