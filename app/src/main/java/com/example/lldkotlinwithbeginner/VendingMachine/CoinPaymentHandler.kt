package com.example.lldkotlinwithbeginner.VendingMachine

class CoinPaymentHandler {
    private var totalInserted = 0

    fun insertCoin(coin: Coin) {
        totalInserted += coin.value
        println("Inserted: ${coin.name}, Total = $totalInserted cents")
    }

    fun getTotalInserted(): Int = totalInserted

    fun hasSufficientAmount(price: Int): Boolean = totalInserted >= price

    fun refund(): Int {
        val refund = totalInserted
        totalInserted = 0
        println("Refunding $refund cents")
        return refund
    }

    fun reset() {
        totalInserted = 0
    }
}
