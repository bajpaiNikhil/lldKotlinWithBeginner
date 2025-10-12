package com.example.lldkotlinwithbeginner.VendingMachine

enum class ItemType {
    COKE, PEPSI, WATER, CHIPS, CHOCOLATE
}

enum class Coin(val value: Int) {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25)
}

enum class MachineState {
    IDLE,
    WAITING_FOR_MONEY,
    VALIDATING_PAYMENT,
    DISPENSING_ITEM,
    RETURNING_MONEY
}
