package com.example.lr3_2_shop.presentation.navigation

import com.example.lr3_2_shop.domain.Order
import kotlinx.serialization.Serializable

object NavigationRoutes {

    @Serializable
    data object ShoppingScreen

    @Serializable
    data class ShoppingCard(
        val orderList: List<Order>
    )

}