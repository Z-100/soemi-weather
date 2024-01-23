package ch.soemiweather

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import java.io.FileNotFoundException

fun main() {
    embeddedServer(Netty, port = 8089) {
        install(CORS) {
            methods.add(HttpMethod.Get)
            allowCredentials = true
            hosts.add("*")
        }
        routing {
            misc()
            getTheStarWarsPlanetsMappingForTheCurrentTemperatureCompletelyAndUtterlyAccurate()
        }
    }.start(wait = true)
}

fun Route.getTheStarWarsPlanetsMappingForTheCurrentTemperatureCompletelyAndUtterlyAccurate() {
    get("/get-the-star-wars-planets-mapping-for-the-current-temperature-completely-and-utterly-accurate") {

        val temp = call.parameters["temp"]

        if (temp.isNullOrBlank()) {
            call.respondText(funnySecretPlanet().json())
        } else {
            call.respondText { getPlanetForTemp(java.lang.Double.parseDouble(temp)).json() }
        }
    }
}

fun Route.misc() {
    get("/") {
        call.respondText { "Pls use /get-the-star-wars-planets-mapping-for-the-current-temperature-completely-and-utterly-accurate?temp={temp}" }
    }

    get("/healthcheck") {
        call.respond("")
    }
}

fun getPlanetForTemp(temp: Double): Planet {

    val planets = File(fromResource("/planets.csv")).readLines()
        .map { it.split(";") }
        .map { Planet(Integer.parseInt(it[0]), it[1], it[2]) }

    return planets.first { it.temp == (5 * Math.round(temp / 5)).toInt() }
}

fun fromResource(uri: String) =
    object {}.javaClass.getResource(uri)?.toURI() ?: throw FileNotFoundException("$uri not found")

fun funnySecretPlanet() =
    Planet(69420, "Yogurt", "https://uploads.dailydot.com/2018/07/yoda-star-wars-meme.jpg?auto=compress&fm=pjpg")

@Serializable
class Planet(
    val temp: Int,
    val name: String,
    val image: String,
) {
    fun json() = Json.encodeToJsonElement(this).toString()
}
