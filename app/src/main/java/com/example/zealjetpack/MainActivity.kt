package com.example.zealjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.zealjetpack.pages.CartPage
import com.example.zealjetpack.pages.HomePage
import com.example.zealjetpack.pages.ProductDetailPage
import com.example.zealjetpack.pages.WelcomePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "welcome"
            ) {
                composable("welcome") {
                    WelcomePage(
                        onGetStartedClick = {
                            navController.navigate("home")
                        }
                    )
                }

                composable("home") {
                    HomePage(
                        onItemClick = { productId ->
                            navController.navigate("Detail/$productId")
                        }
                    )
                }


                composable(
                    route = "detail/{productId}",
                    arguments = listOf(
                        navArgument("productId") { type = NavType.StringType }
                    )
                ) { backStackEntry ->

                    val productId = backStackEntry.arguments?.getString("productId") ?: ""

                    ProductDetailPage(productId = productId, {
                        navController.popBackStack()
                    }, {
                        //navController.navigate("Notification")
                    }, { productId ->
                        navController.navigate("Cart/$productId")
                    })
                }
                composable(
                    route = "Cart/{productId}",
                    arguments = listOf(
                        navArgument("productId") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getString("productId") ?: ""

                    CartPage(productId, {
                        navController.navigate("home")
                    }, {

                    }, {

                    })
                }
            }
        }
    }
}
