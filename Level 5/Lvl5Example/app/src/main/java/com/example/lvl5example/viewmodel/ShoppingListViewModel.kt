package com.example.lvl5example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.lvl5example.data.ShoppingListItem
import com.example.lvl5example.repository.ShoppingListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application): AndroidViewModel(application) {

    private val _ioScope = CoroutineScope(Dispatchers.IO)
    private val _shoppingListRepository: ShoppingListRepository = ShoppingListRepository(application.applicationContext)

    var shoppingListItems: LiveData<List<ShoppingListItem>> = _shoppingListRepository.getAllShoppingListItems()

    fun addShoppingListItem(shoppingListItem: ShoppingListItem) {
        _ioScope.launch {
            _shoppingListRepository.addShoppingListItem(shoppingListItem)
        }
    }

    fun removeShoppingListItem(shoppingListItem: ShoppingListItem){
        _ioScope.launch {
            _shoppingListRepository.removeShoppingListItem(shoppingListItem)
        }
    }

    fun removeAllShoppingListitems() {
        _ioScope.launch {
            _shoppingListRepository.removeAllShoppingListItems()
        }
    }

    fun updateShoppingListitem(shoppingListItem: ShoppingListItem) {
        _ioScope.launch {
            _shoppingListRepository.updateShoppingListItem(shoppingListItem)
        }
    }
}