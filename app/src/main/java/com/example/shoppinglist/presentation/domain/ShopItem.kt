package com.example.shoppinglist.presentation.domain

data class ShopItem(
    val Name: String,
    var Count: Int,
    val State: Boolean,
    var Id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
