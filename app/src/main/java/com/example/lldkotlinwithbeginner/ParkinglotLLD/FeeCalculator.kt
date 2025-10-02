package com.example.lldkotlinwithbeginner.ParkinglotLLD

class FeeCalculator {
    private val hourlyRate = mapOf(
        VehicleType.BIKE to 10,
        VehicleType.CAR to 20,
        VehicleType.TRUCK to 30
    )

    fun calculateFee(ticket: Ticket): Int {
        val exit = ticket.exitTime ?: System.currentTimeMillis()
        val durationHours = ((exit - ticket.entryTime) + 3_599_999) / 3_600_000
        return (durationHours * (hourlyRate[ticket.vehicle.type] ?: 0)).toInt()
    }
}