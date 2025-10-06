package com.example.lldkotlinwithbeginner.BookMyShow

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

// ---------- Core Entities ----------

// Represents a movie with all its details
data class Movie(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val language: String,
    val genre: String,
    val durationMinutes: Int,
    val format: MovieFormat,
    val releaseDate: LocalDate,
    val certificate: Certificate
)

// Represents an individual seat inside a screen
data class Seat (
    val id : UUID = UUID.randomUUID(),
    val row : String ,
    val number : Int,
    val seatType : SeatType
){
    val label : String get() = "$row$number"
}

// Represents a screen inside a theater
data class Screen(
    val id: UUID = UUID.randomUUID(),
    val screenNumber: Int,
    val capacity: Int,
    val screenType: ScreenType,
    val soundSystem: String,
    val seats: List<Seat>
)

// Represents the screen inside an theater :
data class Theater (
    val id :  UUID = UUID.randomUUID(),
    val name : String,
    val city : String,
    val address : String,
    val screens : List<Screen>
)

// Represents a specific showtime for a movie in a given screen and theater
data class Show(
    val id : UUID = UUID.randomUUID(),
    val movie: Movie,
    val theater: Theater,
    val screen: Screen,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)

// Represents a user of the booking system
data class User(
    val id : UUID = UUID.randomUUID(),
    val name : String,
    val email : String
)

// Represents the payment details for booking
data class Payment (
    val id : UUID = UUID.randomUUID(),
    val amount : Double,
    val method : PaymentMethod,
    var status : PaymentStatus,
    val createdAt : LocalDateTime = LocalDateTime.now()
)

// Represents a booking made by a user
data class Booking(
    val id: UUID = UUID.randomUUID(),
    val user: User,
    val theater: Theater,
    val movie: Movie,
    val show: Show,
    val seats: List<Seat>,
    val bookingTime: LocalDateTime = LocalDateTime.now(),
    var status: BookingStatus = BookingStatus.PENDING_PAYMENT,
    var payment: Payment? = null
)

