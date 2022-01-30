package com.example.shoppinglist.presentation.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.presentation.data.shopListRepositoryImpl
import com.example.shoppinglist.presentation.domain.DeleteShopItemUseCase
import com.example.shoppinglist.presentation.domain.EditShopItemUseCase
import com.example.shoppinglist.presentation.domain.GetShopListUseCase
import com.example.shoppinglist.presentation.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = shopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun delItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editItem(shopItem: ShopItem){
        val newItem = shopItem.copy(State = !shopItem.State)
        editShopItemUseCase.editShopItem(newItem)
    }

}