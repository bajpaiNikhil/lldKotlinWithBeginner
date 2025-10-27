package com.example.lldkotlinwithbeginner.elevatorSystem

class ElevatorController(
    private val elevators: List<Elevator>,
    private val scheduler: Scheduler
) {
    fun receiveExternalRequest(request: ExternalRequest) {
        val elevator = scheduler.selectElevator(request, elevators)
        println("Controller assigned Elevator ${elevator.id} to floor ${request.floor}")
        elevator.addExternalRequest(request.floor, request.direction)
    }

    fun receiveInternalRequest(elevatorId: Int, floor: Int) {
        elevators.firstOrNull { it.id == elevatorId }?.addInternalRequest(InternalRequest(floor))
    }
}
