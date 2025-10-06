package com.example.lldkotlinwithbeginner.BookMyShow

// Service to calculate ticket price
interface PricingService {
    fun priceFor(show: Show, seats: List<Seat>): Double
}


class SimplePricingService : PricingService {
    override fun priceFor(show: Show, seats: List<Seat>): Double {
        val base = when (show.screen.screenType) {
            ScreenType.IMAX -> 400.0
            ScreenType.FOUR_DX -> 500.0
            ScreenType.THREE_D -> 300.0
            ScreenType.STANDARD -> 250.0
        }
        return seats.sumOf { seat ->
            val multiplier = when (seat.seatType) {
                SeatType.STANDARD -> 1.0
                SeatType.PREMIUM -> 1.2
                SeatType.RECLINER -> 1.6
                SeatType.VIP -> 2.0
            }
            base * multiplier
        }
    }
}