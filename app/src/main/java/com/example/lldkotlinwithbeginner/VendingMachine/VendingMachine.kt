package com.example.lldkotlinwithbeginner.VendingMachine

class VendingMachine {
    private val inventory = Inventory()
    private val paymentHandler = CoinPaymentHandler()
    private var currentState = MachineState.IDLE

    init {
        // Preload inventory
        inventory.addItem(Item(ItemType.COKE, 25, 5))
        inventory.addItem(Item(ItemType.PEPSI, 35, 5))
        inventory.addItem(Item(ItemType.CHIPS, 20, 5))
    }

    fun insertCoin(coin: Coin) {
        paymentHandler.insertCoin(coin)
        currentState = MachineState.WAITING_FOR_MONEY
    }

    fun selectItem(itemType: ItemType) {
        if (currentState == MachineState.IDLE) {
            println("Please insert coins first.")
            return
        }

        val item = inventory.getItem(itemType)
        if (item == null || !inventory.isAvailable(itemType)) {
            println("Item unavailable. Refunding your money...")
            refund()
            return
        }

        currentState = MachineState.VALIDATING_PAYMENT

        if (paymentHandler.hasSufficientAmount(item.price)) {
            dispenseItem(itemType)
        } else {
            val required = item.price - paymentHandler.getTotalInserted()
            println("Please insert $required more cents.")
        }
    }

    private fun dispenseItem(itemType: ItemType) {
        currentState = MachineState.DISPENSING_ITEM
        inventory.reduceQuantity(itemType)
        println("Dispensing ${itemType.name}")

        val item = inventory.getItem(itemType)!!
        val change = paymentHandler.getTotalInserted() - item.price

        if (change > 0) println("Returning change: $change cents")

        paymentHandler.reset()
        currentState = MachineState.IDLE
        println("Transaction complete. Machine ready for next customer.")
    }

    fun refund() {
        currentState = MachineState.RETURNING_MONEY
        paymentHandler.refund()
        currentState = MachineState.IDLE
    }
}
