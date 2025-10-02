package com.example.lldkotlinwithbeginner.ParkinglotLLD

import java.util.UUID

class Ticket(
    val ticketId: String = UUID.randomUUID().toString(),
    val vehicle: VehicleAbstractClass,
    val spot: ParkingSpot,
    val entryTime: Long = System.currentTimeMillis(),
    var exitTime: Long? = null
)