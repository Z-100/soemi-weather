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
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets.UTF_8

const val MAX_TEMP = 55
const val MIN_TEMP = -90

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

    val planets = getFileContent("planets.csv")
        .map { it.split(";") }
        .map { Planet(Integer.parseInt(it[0]), it[1], it[2]) }

    val planet = planets.firstOrNull { it.temp == 5 * Math.round(temp / 5).toInt() }
        ?: planets.first { if (temp > 0) it.temp == MAX_TEMP else it.temp == MIN_TEMP }

    return planet
}

fun getFileContent(uri: String): List<String> {
    val stream = Thread.currentThread().contextClassLoader.getResourceAsStream(uri)
        ?: throw FileNotFoundException("$uri not found")

    InputStreamReader(stream, UTF_8).use {
        return BufferedReader(InputStreamReader(stream)).readLines()
    }
}

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
