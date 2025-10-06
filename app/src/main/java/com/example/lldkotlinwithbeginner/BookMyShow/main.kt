package com.example.lldkotlinwithbeginner.BookMyShow

import java.time.LocalDate

fun main() {
    val user = User(name = "Alice", email = "alice@example.com")


    val showRepo = InMemoryShowRepository(SampleDataFactory.showsFor("Bengaluru").toMutableList())
    val bookingRepo = InMemoryBookingRepository()
    val seatLocks = InMemorySeatLockingSercvice()
    val availability = DefaultAvailabilityService(bookingRepo, seatLocks)
    val pricing = SimplePricingService()
    val payments = DummyPaymentGateway()
    val bookingService = DefaultBookingService(seatLocks, bookingRepo, availability, pricing, payments)
    val search = DefaultSearchService(showRepo)


    val city = "Bengaluru"
    val today = LocalDate.now()
    val movies = search.searchMoviesByCity(city, today)
    println("Movies in $city today: ${movies.map { it.title }}")


    val show = search.searchShows(movies.first(), city, today).first()
    val seatsToBook = availability.availableSeats(show).take(2)


    val booking = bookingService.book(user, show, seatsToBook, PaymentMethod.UPI)
    println("Booking CONFIRMED: id=${booking.id}, seats=${booking.seats.map { it.label }}, amount=${booking.payment?.amount}")
}