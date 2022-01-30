package com.example.shoppinglist.presentation.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.presentation.domain.ShopItem
import com.example.shoppinglist.presentation.domain.ShopListRepository

object shopListRepositoryImpl: ShopListRepository{

    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.Id.compareTo(o2.Id) })
    private val shopListLD =MutableLiveData<List<ShopItem>>()
    private var autoIncrementId: Int = 0

    init {
        for(i in 0..1000){
            val item = ShopItem("Name$i",i,true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.Id == ShopItem.UNDEFINED_ID){
            shopItem.Id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun delShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.Id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.Id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}