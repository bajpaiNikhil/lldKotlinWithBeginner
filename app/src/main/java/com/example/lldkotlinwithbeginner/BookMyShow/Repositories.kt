package com.example.lldkotlinwithbeginner.BookMyShow

import java.time.LocalDate
import java.util.UUID


// Repository interfaces for data persistence
interface MovieRepository {
    fun findByCityAndDate(city: String, date: LocalDate): List<Movie>
}

interface TheaterRepository {
    fun findByCity(city: String): List<Theater>
}

interface ShowRepository {
    fun findShows(movie: Movie?, city: String?, date: LocalDate?): List<Show>
    fun findById(id: UUID): Show?
}

interface BookingRepository {
    fun save(booking: Booking): Booking
    fun findById(id: UUID): Booking?
    fun findByShow(show: Show): List<Booking>
}

// In-memory implementation of repositories for testing/demo
class InMemoryShowRepository(private val shows: MutableList<Show> = mutableListOf()) : ShowRepository {
    override fun findShows(movie: Movie?, city: String?, date: LocalDate?): List<Show> =
        shows.filter { s ->
            (movie == null || s.movie.id == movie.id) &&
                    (city == null || s.theater.city.equals(city, ignoreCase = true)) &&
                    (date == null || s.startTime.toLocalDate() == date)
        }


    override fun findById(id: UUID): Show? = shows.firstOrNull { it.id == id }
}


class InMemoryBookingRepository : BookingRepository {
    private val store = mutableMapOf<UUID, Booking>()
    override fun save(booking: Booking): Booking { store[booking.id] = booking; return booking }
    override fun findById(id: UUID): Booking? = store[id]
    override fun findByShow(show: Show): List<Booking> = store.values.filter { it.show.id == show.id }
}
