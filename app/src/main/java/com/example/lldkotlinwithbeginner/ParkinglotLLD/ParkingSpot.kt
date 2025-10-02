package com.example.lldkotlinwithbeginner.ParkinglotLLD

class ParkingSpot(
    val spotId: String,
    val spotType: VehicleType,
    var isAvailable: Boolean = true,
    var currentVehicle: VehicleAbstractClass? = null
) {
    fun parkVehicle(vehicle: VehicleAbstractClass) {
        currentVehicle = vehicle
        isAvailable = false
    }

    fun removeVehicle() {
        currentVehicle = null
        isAvailable = true
    }
}