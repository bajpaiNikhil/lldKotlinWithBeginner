package com.example.lldkotlinwithbeginner.elevatorSystem

fun main() {
    val elevators = listOf(Elevator(1, 10), Elevator(2, 10))
    val controller = ElevatorController(elevators, NearestCarScheduler())

    controller.receiveExternalRequest(ExternalRequest(3, Direction.UP))
    Thread.sleep(500)
    controller.receiveExternalRequest(ExternalRequest(7, Direction.DOWN))
    Thread.sleep(500)
    controller.receiveInternalRequest(1, 9)
}
