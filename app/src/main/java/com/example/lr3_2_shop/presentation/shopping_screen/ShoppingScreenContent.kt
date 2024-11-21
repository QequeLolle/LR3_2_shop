package com.example.lr3_2_shop.presentation.shopping_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lr3_2_shop.domain.Order
import com.example.lr3_2_shop.presentation.navigation.NavigationRoutes
import com.example.lr3_2_shop.ui.theme.Lr3_2_shopTheme

@Composable
fun ShoppingScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ShoppingScreenViewModel = viewModel()
) {
    val orderList by viewModel.orderList.collectAsState()
    ShoppingScreen(
        orderListSize = orderList.size,
        onNextClick = { viewModel.addOrderToList(it) },
        onFinishClick = { navController.navigate(NavigationRoutes.ShoppingCard(orderList)) },
        modifier = modifier
    )
}

@Composable
fun ShoppingScreen(
    orderListSize: Int,
    onNextClick: (Order) -> Unit,
    onFinishClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }

    var price by rememberSaveable {
        mutableStateOf("")
    }

    var adultTicketsCount by rememberSaveable {
        mutableStateOf("")
    }

    var isValid by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(40.dp)
    ) {
        Text(
            text = "Количество оформленных заказов: $orderListSize",
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier
                .height(12.dp)
        )

        InputField(
            text = "Название товара:",
            value = name,
            onValueChanged = { name = it },
            keyboardType = KeyboardType.Text
        )

        InputField(
            text = "Количество товаров:",
            value = adultTicketsCount,
            onValueChanged = { adultTicketsCount = it },
            keyboardType = KeyboardType.Decimal
        )

        InputField(
            text = "Общая цена товара:",
            value = price,
            onValueChanged = { price = it },
            keyboardType = KeyboardType.Decimal
        )

        Button(
            onClick = {
                if (name.isEmpty() || price.isEmpty() || adultTicketsCount.isEmpty()) {
                    isValid = false
                }
                if (isValid) {
                    val order = Order(
                        name,
                        price.toDouble(),
                        adultTicketsCount.toInt(),
                    )
                    onNextClick(order)
                    name = ""
                    price = ""
                    adultTicketsCount = ""
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Перейти к новому заказу")
        }

        Button(
            onClick = { onFinishClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Просмотреть итог")
        }
    }
}

@Composable
fun InputField(
    text: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(12.dp)
    ) {
        Text(
            text = text
        )

        TextField(
            value = value,
            onValueChange = { onValueChanged(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ShoppingScreenPreview() {
    Lr3_2_shopTheme {
        ShoppingScreen(
            orderListSize = 16,
            onNextClick = {},
            onFinishClick = {}
        )
    }
}