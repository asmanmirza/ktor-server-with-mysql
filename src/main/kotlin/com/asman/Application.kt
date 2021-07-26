package com.asman

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.asman.plugins.*

fun main() {
    embeddedServer(Netty, port = 80, host = "127.0.0.1") {
        configureRouting()
        configureSerialization()
        configureAdministration()
        configureMonitoring()
        configureHTTP()
        configureSecurity()
    }.start(wait = true)
}
