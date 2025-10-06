package com.example.lldkotlinwithbeginner.BookMyShow

import java.time.LocalDate

// Defines search operations like movies by city, theaters by movie etc.
interface SearchService {
    fun searchMoviesByCity(city: String, date: LocalDate): List<Movie>
    fun searchTheatersByMovie(movie: Movie, city: String): List<Theater>
    fun searchShows(movie: Movie, city: String, date: LocalDate): List<Show>
}

// Default search service implementation using repositories
class DefaultSearchService(
    private val showRepo: ShowRepository
) : SearchService {
    override fun searchMoviesByCity(city: String, date: LocalDate): List<Movie> =
        showRepo.findShows(null, city, date).map { it.movie }.distinctBy { it.id }

    override fun searchTheatersByMovie(movie: Movie, city: String): List<Theater> =
        showRepo.findShows(movie, city, null).map { it.theater }.distinctBy { it.id }

    override fun searchShows(movie: Movie, city: String, date: LocalDate): List<Show> =
        showRepo.findShows(movie, city, date)
}