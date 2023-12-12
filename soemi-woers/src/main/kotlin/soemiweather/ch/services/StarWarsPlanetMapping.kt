package soemiweather.ch.services

import soemiweather.ch.models.Planet
import java.io.File
import java.io.FileNotFoundException

fun getPlanetForTemp(temp: Double): Planet {

    val planetsUri = object {}.javaClass.getResource("/planets.csv")?.toURI()
        ?: throw FileNotFoundException("planets.csv doesn't exist!")

    val planets = File(planetsUri).readLines()
        .map { it.split(";") }
        .map { Planet(Integer.parseInt(it[0]), it[1], it[2]) }

    return planets.minByOrNull { temp - temp % 5 }!!
}
