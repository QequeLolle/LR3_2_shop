package com.example.lr3_2_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.lr3_2_shop.domain.Order
import com.example.lr3_2_shop.presentation.navigation.CustomNavType
import com.example.lr3_2_shop.presentation.navigation.NavigationRoutes
import com.example.lr3_2_shop.presentation.shopping_card.ShoppingCardContent
import com.example.lr3_2_shop.presentation.shopping_screen.ShoppingScreenContent
import com.example.lr3_2_shop.ui.theme.Lr3_2_shopTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lr3_2_shopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.ShoppingScreen
                    ) {

                        composable<NavigationRoutes.ShoppingScreen> {
                            ShoppingScreenContent(
                                navController = navController,
                                modifier = Modifier
                                    .padding(innerPadding)
                            )
                        }

                        composable<NavigationRoutes.ShoppingCard>(
                            typeMap = mapOf(
                                typeOf<List<Order>>() to CustomNavType.OrdersListType
                            )
                        ) {
                            val arguments = it.toRoute<NavigationRoutes.ShoppingCard>()
                            ShoppingCardContent(
                                ordersList = arguments.orderList,
                                modifier = Modifier
                                    .padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}
