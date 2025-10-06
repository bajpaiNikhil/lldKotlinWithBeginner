package com.example.lldkotlinwithbeginner.BookMyShow

// Service to check seat availability
interface AvailabilityService {
    fun availableSeats(show: Show): List<Seat>
}


class DefaultAvailabilityService(
    private val bookingRepo: BookingRepository,
    private val seatLockService: SeatLockingService
) : AvailabilityService {
    override fun availableSeats(show: Show): List<Seat> {
        val bookedSeats = bookingRepo.findByShow(show).filter { it.status == BookingStatus.CONFIRMED }
            .flatMap { it.seats }.map { it.id }.toSet()
        val lockedSeats = seatLockService.getLockedSeat(show).map { it.id }.toSet()
        return show.screen.seats.filter { it.id !in bookedSeats && it.id !in lockedSeats }
    }
}