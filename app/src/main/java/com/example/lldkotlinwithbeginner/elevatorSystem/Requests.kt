package com.example.lldkotlinwithbeginner.elevatorSystem

// Base request class
abstract class Request(val floor: Int)

// External request - from floor buttons
class ExternalRequest(floor: Int, val direction: Direction) : Request(floor)

// Internal request - from inside elevator panel
class InternalRequest(floor: Int) : Request(floor)
