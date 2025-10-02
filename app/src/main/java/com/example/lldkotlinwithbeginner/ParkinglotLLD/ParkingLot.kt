package com.example.lldkotlinwithbeginner.ParkinglotLLD

class ParkingLot {
    val floors = mutableListOf<Floor>()

    fun allocateSpot(type: VehicleType): ParkingSpot? {
        for (floor in floors) {
            val spot = floor.findAvailableSpot(type)
            if (spot != null) return spot
        }
        return null
    }
}