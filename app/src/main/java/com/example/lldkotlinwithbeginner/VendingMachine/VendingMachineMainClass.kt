package com.example.lldkotlinwithbeginner.VendingMachine

fun main() {
    val machine = VendingMachine()

    machine.insertCoin(Coin.QUARTER)
    machine.selectItem(ItemType.COKE)

    println("----")

    machine.insertCoin(Coin.DIME)
    machine.insertCoin(Coin.QUARTER)
    machine.selectItem(ItemType.PEPSI)

    println("----")

    machine.insertCoin(Coin.NICKEL)
    machine.selectItem(ItemType.CHIPS)
}
