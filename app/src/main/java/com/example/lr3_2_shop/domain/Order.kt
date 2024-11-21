package com.example.lr3_2_shop.domain

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val name: String, // название товара
    val price: Double,  // цена всего заказа
    val adultTicketsCount: Int,  // количество товаров
)
