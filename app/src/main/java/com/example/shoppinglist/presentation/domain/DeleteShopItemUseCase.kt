package com.example.shoppinglist.presentation.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.delShopItem(shopItem)
    }
}