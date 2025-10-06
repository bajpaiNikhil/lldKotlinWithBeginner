package com.example.lldkotlinwithbeginner.BookMyShow

import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID

// Represent a temporary lock on to the seat to present from double booking

data class SeatLock(
    val seat : Seat,
    val show : Show,
    val lockedBy : User,
    val lockTime : LocalDateTime = LocalDateTime.now(),
    val duration: Duration = Duration.ofMinutes(5)
){
    fun isExpired(now : LocalDateTime = LocalDateTime.now()): Boolean {
        return now.isAfter(lockTime.plus(duration))
    }
}

// Defines contract for seat lock management
interface SeatLockingService{

    fun lockSeats(show : Show, seats : List<Seat>, user: User)

    fun releaseSeat(show: Show , seats : List<Seat> , user: User)

    fun validateLock(show : Show , seats : List<Seat> , user: User) : Boolean

    fun getLockedSeat(show: Show) : Set<Seat>

}

// In-memory seat lock service implementation
class InMemorySeatLockingSercvice : SeatLockingService{
    private val lockMap: MutableMap<UUID, MutableMap<UUID, SeatLock>> = mutableMapOf()
    private val mutex = Any()

    override fun lockSeats(
        show: Show,
        seats: List<Seat>,
        user: User
    ) {
        synchronized(mutex){
            purgeExpired(show)
            val showLocks = lockMap.getOrPut(show.id) { mutableMapOf()  }
            seats.forEach { seat ->
                val existing = showLocks[show.id]
                if (existing != null && !existing.isExpired() && existing.lockedBy != user) {
                    error("Seat ${seat.label} is already locked")
                }

            }
            seats.forEach { seat ->
                showLocks[seat.id] = SeatLock(seat, show, user)
            }
        }
    }

    override fun releaseSeat(
        show: Show,
        seats: List<Seat>,
        user: User
    ) {
        synchronized(mutex) {
            val showLocks = lockMap[show.id] ?: return
            seats.forEach { seat ->
                val existing = showLocks[seat.id]
                if (existing != null && existing.lockedBy == user) {
                    showLocks.remove(seat.id)
                }
            }
        }
    }

    override fun validateLock(
        show: Show,
        seats: List<Seat>,
        user: User
    ): Boolean = synchronized(mutex) {
        purgeExpired(show)
        val showLocks = lockMap[show.id] ?: return@synchronized false
        seats.all { seat ->
            val existing = showLocks[seat.id]
            existing != null && !existing.isExpired() && existing.lockedBy == user
        }
    }


    override fun getLockedSeat(show: Show): Set<Seat> = synchronized(mutex) {
        purgeExpired(show)
        lockMap[show.id]?.values?.map { it.seat }?.toSet() ?: emptySet()
    }

    private fun purgeExpired(show: Show) {
        val showLocks = lockMap[show.id] ?: return
        val it = showLocks.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            if (entry.value.isExpired()) it.remove()
        }
    }

}