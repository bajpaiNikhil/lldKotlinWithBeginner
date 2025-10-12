package com.example.lldkotlinwithbeginner.VendingMachine

data class Item(
    val type: ItemType,
    val price: Int,
    var quantity: Int
)

class Inventory {
    private val items = mutableMapOf<ItemType, Item>()

    fun addItem(item: Item) {
        items[item.type] = item
    }

    fun getItem(itemType: ItemType): Item? = items[itemType]

    fun isAvailable(itemType: ItemType): Boolean {
        val item = items[itemType]
        return item != null && item.quantity > 0
    }

    fun reduceQuantity(itemType: ItemType) {
        val item = items[itemType]
        if (item != null && item.quantity > 0) {
            item.quantity--
        } else {
            throw Exception("Item not available")
        }
    }
}
