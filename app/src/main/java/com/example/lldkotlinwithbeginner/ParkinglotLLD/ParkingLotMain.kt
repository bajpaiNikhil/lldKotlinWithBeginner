package com.example.lldkotlinwithbeginner.ParkinglotLLD


// Problem Statement :
// Design a Parking Lot System
// A parking lot has multiple floors. Each floor has multiple parking spots that can accommodate
// different types of vehicles (e.g., bikes, cars, trucks).
// The system should:
// Let vehicles enter and get a parking ticket.
//
// Let vehicles exit and calculate the parking fee.
//
// Track available spots per floor and per vehicle type.

fun main() {
    // 1. Setup Parking Lot
    val parkingLot = ParkingLot()

    // Add 2 floors, each with 2 spots (1 Car, 1 Bike for simplicity)
    repeat(2) { floorNum ->
        val floor = Floor(floorNum + 1)
        floor.spots.add(ParkingSpot("C${floorNum}1", VehicleType.CAR))
        floor.spots.add(ParkingSpot("B${floorNum}1", VehicleType.BIKE))
        parkingLot.floors.add(floor)
    }

    // Create gates
    val entranceGate = EntranceGate(parkingLot)
    val exitGate = ExitGate()

    // 2. Vehicle enters
    val car = Car("MH12AB1234")
    val ticket = entranceGate.issueTicket(car)
    println("Ticket issued for ${car.type} at spot ${ticket.spot.spotId}, TicketId: ${ticket.ticketId}")

    // 3. Simulate some parking time
    Thread.sleep(2000) // 2 seconds wait to simulate parking duration

    // 4. Vehicle exits
    val fee = exitGate.processExit(ticket)
    println("Vehicle ${car.licensePlate} exiting. Fee: $fee")
}
