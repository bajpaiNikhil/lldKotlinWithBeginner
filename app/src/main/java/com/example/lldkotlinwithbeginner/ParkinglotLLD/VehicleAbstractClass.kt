package com.example.lldkotlinwithbeginner.ParkinglotLLD

// Enum for types
enum class VehicleType { BIKE, CAR, TRUCK }

// Abstract parent
abstract class VehicleAbstractClass(
    val licensePlate: String,
    val type: VehicleType,
    var entryTime: Long = System.currentTimeMillis()
)

class Bike(plate: String) : VehicleAbstractClass(plate, VehicleType.BIKE)
class Car(plate: String) : VehicleAbstractClass(plate, VehicleType.CAR)
class Truck(plate: String) : VehicleAbstractClass(plate, VehicleType.TRUCK)