package com.example.lr3_2_shop.presentation.shopping_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.lr3_2_shop.domain.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoppingScreenViewModel : ViewModel() {
    private val _orderList: MutableStateFlow<List<Order>> = MutableStateFlow(listOf())
    val orderList: StateFlow<List<Order>> = _orderList.asStateFlow()

    fun addOrderToList(order: Order) {
        val interList = _orderList.value.toMutableList()
        interList.add(order)
        _orderList.value = interList.toList()
    }
}