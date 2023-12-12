package soemiweather.ch.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import soemiweather.ch.services.getPlanetForTemp
import java.lang.Double.parseDouble

fun Application.configureSoemiRoutes() {
    routing {
        get("/get-the-star-wars-planets-mapping-for-the-current-temperature-completely-and-utterly-accurate/") {

            val temp = call.parameters["temp"]

            if (temp == null) {
                call.respondText("https://uploads.dailydot.com/2018/07/yoda-star-wars-meme.jpg?auto=compress&fm=pjpg")
            }

            call.respondText { getPlanetForTemp(parseDouble(temp)).toString() }
        }
    }
}
