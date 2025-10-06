package com.example.lldkotlinwithbeginner.BookMyShow

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

// ---------- Sample Data Builder ----------


// Utility to generate sample data for testing/demo
object SampleDataFactory {
    fun seatGrid(rows: Int = 5, cols: Int = 10): List<Seat> {
        val rowsLabels = ('A' until ('A' + rows)).map { it.toString() }
        return rowsLabels.flatMap { r ->
            (1..cols).map { c ->
                val type = when {
                    r == "A" -> SeatType.VIP
                    r == "B" -> SeatType.RECLINER
                    c > cols - 2 -> SeatType.PREMIUM
                    else -> SeatType.STANDARD
                }
                Seat(row = r, number = c, seatType = type)
            }
        }
    }


    fun screen(number: Int, type: ScreenType): Screen = Screen(
        screenNumber = number,
        capacity = 50,
        screenType = type,
        soundSystem = "Dolby Atmos",
        seats = seatGrid(5, 10)
    )


    fun theater(city: String, name: String = "PVR ${city.uppercase()}"): Theater = Theater(
        name = name,
        city = city,
        address = "Central Mall, $city",
        screens = listOf(screen(1, ScreenType.IMAX), screen(2, ScreenType.STANDARD))
    )


    fun movie(title: String, format: MovieFormat, language: String = "English"): Movie = Movie(
        title = title,
        language = language,
        genre = "Action",
        durationMinutes = 150,
        format = format,
        releaseDate = LocalDate.now().minusDays(10),
        certificate = Certificate.UA
    )


    fun showsFor(city: String): List<Show> {
        val t = theater(city)
        val m = movie("Inception", MovieFormat.IMAX)
        val start = LocalDateTime.now().withHour(19).withMinute(0).withSecond(0).withNano(0)
        return listOf(
            Show(movie = m, theater = t, screen = t.screens.first(), startTime = start, endTime = start.plusMinutes(m.durationMinutes.toLong())),
            Show(movie = m.copy(id = UUID.randomUUID(), title = "Dune"), theater = t, screen = t.screens[1], startTime = start.plusHours(1), endTime = start.plusHours(1).plusMinutes(155))
        )
    }
}