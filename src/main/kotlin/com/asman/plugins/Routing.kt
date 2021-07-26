package com.asman.plugins

import com.asman.database.tables.LoginTable
import com.asman.models.Login
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {


    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/login") {
            val data1 = call.receive<Login>()
            transaction {
                val isExist = LoginTable.select { (LoginTable.username eq data1.username) and (LoginTable.password eq data1.password) }.count()
                if (isExist > 0) {
                    launch {
                        call.respond(HttpStatusCode.Accepted, "successfully logged in!")
                    }
                }
            }
        }


        install(StatusPages) {
            exception<AuthenticationException> {
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> {
                call.respond(HttpStatusCode.Forbidden)
            }

        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
