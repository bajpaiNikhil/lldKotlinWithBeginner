package com.example.lldkotlinwithbeginner.elevatorSystem

import java.util.PriorityQueue

class Elevator(val id: Int, private val totalFloors: Int) {
    var currentFloor = 0
    var direction = Direction.IDLE
    var doorStatus = DoorStatus.CLOSED
    var status = ElevatorStatus.IDLE

    private val upQueue = PriorityQueue<Int>()             // ascending
    private val downQueue = PriorityQueue<Int>(compareByDescending { it }) // descending

    fun addInternalRequest(request: InternalRequest) {
        if (request.floor == currentFloor) return  // already here

        if (request.floor > currentFloor) upQueue.add(request.floor)
        else downQueue.add(request.floor)

        if (status == ElevatorStatus.IDLE) decideNextAction()
    }

    fun moveNext() {
        when (direction) {
            Direction.UP -> {
                if (upQueue.isNotEmpty()) {
                    val nextFloor = upQueue.poll()
                    moveToFloor(nextFloor)
                } else decideNextAction()
            }
            Direction.DOWN -> {
                if (downQueue.isNotEmpty()) {
                    val nextFloor = downQueue.poll()
                    moveToFloor(nextFloor)
                } else decideNextAction()
            }
            Direction.IDLE -> decideNextAction()
        }
    }

    private fun moveToFloor(target: Int) {
        println("Elevator $id moving from $currentFloor to $target")
        status = ElevatorStatus.MOVING
        direction = if (target > currentFloor) Direction.UP else Direction.DOWN

        // simulate movement (in real-world, sensors would update position)
        currentFloor = target
        arriveAtFloor(target)
    }

    private fun arriveAtFloor(floor: Int) {
        println("Elevator $id arrived at floor $floor")
        openDoor()
    }

    private fun openDoor() {
        doorStatus = DoorStatus.OPEN
        println("Elevator $id doors opening at floor $currentFloor")
        Thread.sleep(200) // simulate delay
        closeDoor()
    }

    private fun closeDoor() {
        doorStatus = DoorStatus.CLOSED
        println("Elevator $id doors closing at floor $currentFloor")
        decideNextAction()
    }

    private fun decideNextAction() {
        when {
            upQueue.isNotEmpty() -> direction = Direction.UP
            downQueue.isNotEmpty() -> direction = Direction.DOWN
            else -> {
                direction = Direction.IDLE
                status = ElevatorStatus.IDLE
                println("Elevator $id is now idle at floor $currentFloor")
                return
            }
        }
        moveNext()
    }

    fun addExternalRequest(floor: Int, dir: Direction) {
        // Handle request directionally
        if (dir == Direction.UP && floor >= currentFloor) upQueue.add(floor)
        else if (dir == Direction.DOWN && floor <= currentFloor) downQueue.add(floor)
        else upQueue.add(floor) // default

        if (status == ElevatorStatus.IDLE) decideNextAction()
    }
}
