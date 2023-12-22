package ch.soemiweather

import org.junit.Assert.assertEquals
import org.junit.Test

class ApplicationKtTest {

    @Test
    fun `Test getPlanetForTemp delivers correct planet`() {

        val mustafar = getPlanetForTemp(55.0)
        val sullust = getPlanetForTemp(0.0)
        val hoth = getPlanetForTemp(-90.0)
        val kashyyyk = getPlanetForTemp(-40.0)
        val jakku = getPlanetForTemp(13.0)
        val naboo = getPlanetForTemp(-23.0)
        val takodana = getPlanetForTemp(22.49)

        assertEquals("Mustafar", mustafar.name)
        assertEquals("Sullust", sullust.name)
        assertEquals("Hoth", hoth.name)
        assertEquals("Kashyyyk", kashyyyk.name)

        assertEquals("Jakku", jakku.name)
        assertEquals("Naboo", naboo.name)
        assertEquals("Takodana", takodana.name)
    }
}
