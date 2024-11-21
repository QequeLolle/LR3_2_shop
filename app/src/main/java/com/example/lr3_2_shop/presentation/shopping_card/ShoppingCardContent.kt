package com.example.lr3_2_shop.presentation.shopping_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lr3_2_shop.domain.Order

@Composable
fun ShoppingCardContent(
    ordersList: List<Order>,
    modifier: Modifier = Modifier
) {
    ShoppingCardScreen(
        ordersList = ordersList,
        modifier = modifier
    )
}

@Composable
fun ShoppingCardScreen(
    ordersList: List<Order>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        Text(
            text = "Общая стоимость товаров в заказах: ${ordersList.sumOf { it.price }}"
        )

        Spacer(
            modifier = Modifier
                .height(12.dp)
        )

        Text(
            text = "Общее количество товаров в заказах: ${ordersList.sumOf { it.adultTicketsCount }}"
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Text(
            "Список заказов",
            fontSize = 24.sp
        )

        // отображаем список всех заказов
        LazyColumn {
            items(ordersList) {
                ListItemContainer(it)
            }
        }
    }
}

@Composable
fun ListItemContainer(
    order: Order,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = "Название товара: ${order.name}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "Количество товаров: ${order.adultTicketsCount}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "Общая цена товара: ${order.price}",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ShoppingCardScreenPreview() {
    ShoppingCardScreen(
        listOf()
    )
}
