package com.example.lldkotlinwithbeginner.ParkinglotLLD




class EntranceGate(private val lot: ParkingLot) {
    fun issueTicket(vehicle: VehicleAbstractClass): Ticket {
        val spot = lot.allocateSpot(vehicle.type)
            ?: throw IllegalStateException("No spot available")
        spot.parkVehicle(vehicle)
        return Ticket(vehicle = vehicle, spot = spot)
    }
}

class ExitGate(private val calculator: FeeCalculator = FeeCalculator()) {
    fun processExit(ticket: Ticket): Int {
        ticket.exitTime = System.currentTimeMillis()
        val fee = calculator.calculateFee(ticket)
        ticket.spot.removeVehicle()
        return fee
    }
}