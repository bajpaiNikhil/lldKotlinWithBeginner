package com.example.lldkotlinwithbeginner.elevatorSystem

interface Scheduler {
    fun selectElevator(request: ExternalRequest, elevators: List<Elevator>): Elevator
}

class NearestCarScheduler : Scheduler {
    override fun selectElevator(request: ExternalRequest, elevators: List<Elevator>): Elevator {
        return elevators.minByOrNull {
            when (it.direction) {
                Direction.IDLE -> Math.abs(it.currentFloor - request.floor)
                request.direction -> Math.abs(it.currentFloor - request.floor)
                else -> Int.MAX_VALUE
            }
        }!!
    }
}
