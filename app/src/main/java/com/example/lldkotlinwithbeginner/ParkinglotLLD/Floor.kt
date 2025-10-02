package com.example.lldkotlinwithbeginner.ParkinglotLLD

class Floor(val floorNumber: Int) {
    val spots = mutableListOf<ParkingSpot>()

    fun findAvailableSpot(type: VehicleType): ParkingSpot? {
        return spots.firstOrNull { it.isAvailable && it.spotType == type }
    }
}