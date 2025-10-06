package com.example.lldkotlinwithbeginner.BookMyShow

import java.util.UUID


// ---------- Booking Service ----------


// Exception for booking related errors
class BookingException(message: String) : RuntimeException(message)


// Payment gateway interface
interface PaymentGateway {
    fun charge(amount: Double, method: PaymentMethod): PaymentStatus
}


// Dummy payment gateway implementation
class DummyPaymentGateway : PaymentGateway {
    override fun charge(amount: Double, method: PaymentMethod): PaymentStatus {
        return PaymentStatus.SUCCESS
    }
}


// Service to manage bookings
interface BookingService {
    fun book(user: User, show: Show, seats: List<Seat>, method: PaymentMethod): Booking
    fun cancel(bookingId: UUID)
}

// Default booking service implementation
class DefaultBookingService(
    private val seatLockService: SeatLockingService,
    private val bookingRepo: BookingRepository,
    private val availability: AvailabilityService,
    private val pricing: PricingService,
    private val payments: PaymentGateway
) : BookingService {


    override fun book(user: User, show: Show, seats: List<Seat>, method: PaymentMethod): Booking {
        if (seats.isEmpty()) throw BookingException("Select at least one seat")


        seatLockService.lockSeats(show, seats, user)
        val available = availability.availableSeats(show).map { it.id }.toSet()
        if (!seats.all { it.id in available || seatLockService.validateLock(show, listOf(it), user) }) {
            seatLockService.releaseSeat(show, seats, user)
            throw BookingException("One or more seats became unavailable")
        }


        val amount = pricing.priceFor(show, seats)
        val payment = Payment(amount = amount, method = method ,status = payments.charge(amount, method))


        val booking = Booking(
            user = user,
            theater = show.theater,
            movie = show.movie,
            show = show,
            seats = seats,
            payment = payment,
            status = if (payment.status == PaymentStatus.SUCCESS) BookingStatus.CONFIRMED else BookingStatus.PENDING_PAYMENT
        )
        bookingRepo.save(booking)


        if (payment.status == PaymentStatus.SUCCESS) {
            seatLockService.releaseSeat(show, seats, user)
            return booking
        } else {
            seatLockService.releaseSeat(show, seats, user)
            throw BookingException("Payment failed")
        }
    }


    override fun cancel(bookingId: UUID) {
        val existing = bookingRepo.findById(bookingId) ?: throw BookingException("Booking not found")
        if (existing.status != BookingStatus.CONFIRMED) throw BookingException("Only confirmed bookings can be cancelled")
        existing.status = BookingStatus.CANCELLED
        existing.payment?.status = PaymentStatus.REFUNDED
        bookingRepo.save(existing)
    }
}