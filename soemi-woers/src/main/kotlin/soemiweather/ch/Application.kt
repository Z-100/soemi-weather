package soemiweather.ch

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import soemiweather.ch.routes.*

fun main() {
    embeddedServer(Netty, port = 69420, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSoemiRoutes()
}
