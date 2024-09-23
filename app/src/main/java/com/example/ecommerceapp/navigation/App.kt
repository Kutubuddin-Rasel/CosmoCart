package com.example.ecommerceapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chat.screens.LogIn
import com.example.chat.screens.SingIn
import com.example.ecommerceapp.Admin.home
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.bottombar
import com.example.ecommerceapp.screens.Camera
import com.example.ecommerceapp.screens.Cart
import com.example.ecommerceapp.screens.Earbuds
import com.example.ecommerceapp.screens.Headphone
import com.example.ecommerceapp.screens.AllCategories
import com.example.ecommerceapp.screens.Home
import com.example.ecommerceapp.screens.Kameez
import com.example.ecommerceapp.screens.Keyboard
import com.example.ecommerceapp.screens.Mouse
import com.example.ecommerceapp.screens.Panjabi
import com.example.ecommerceapp.screens.Pant
import com.example.ecommerceapp.screens.Polo
import com.example.ecommerceapp.screens.Profile
import com.example.ecommerceapp.screens.SalwarKameez
import com.example.ecommerceapp.screens.Saree
import com.example.ecommerceapp.screens.Search
import com.example.ecommerceapp.screens.Shirt
import com.example.ecommerceapp.screens.Smart_watch
import com.example.ecommerceapp.screens.Speaker
import com.example.ecommerceapp.screens.Tshirt
import com.example.ecommerceapp.screens.Tv
import com.example.ecommerceapp.screens.product

@Composable
fun App() {
    val navController = rememberNavController()
    var showBottombar by remember { mutableStateOf(true) }
    val navlist = listOf(
        bottombar("Home",R.drawable.home,Routes.home),
        bottombar("Categories", R.drawable.category, Routes.allCategories),
        bottombar("Search\n Products", R.drawable.search, Routes.search),
        bottombar("Cart", R.drawable.cart, Routes.cart)
        )
    Scaffold(
        bottomBar = {
            if (showBottombar) {
                BottomNavigationBar(navController,navlist)
            }
        }
    ) {
        NavHost(navController, Routes.login, modifier = Modifier.padding(it)) {
            composable(Routes.login) {
                LogIn(navController, Routes.login)
            }
            composable(Routes.singIn) {
                SingIn(navController, Routes.singIn)
            }
            composable(Routes.home) {
                Home(navController)
            }
            composable(Routes.allCategories) {
                AllCategories(navController)
            }
            composable(Routes.shirt) {
                Shirt(navController)
            }
            composable(Routes.pant) {
                Pant(navController)
            }
            composable(Routes.panjabi) {
                Panjabi(navController)
            }
            composable(Routes.polo) {
                Polo(navController)
            }
            composable(Routes.t_shirt) {
                Tshirt(navController)
            }
            composable(Routes.salwarKameez) {
                SalwarKameez(navController)
            }
            composable(Routes.kameez) {
                Kameez(navController)
            }
            composable(Routes.saree) {
                Saree(navController)
            }
            composable(Routes.camera) {
                Camera(navController)
            }
            composable(Routes.earbuds) {
                Earbuds(navController)
            }
            composable(Routes.tv) {
                Tv(navController)
            }
            composable(Routes.smartWatch) {
                Smart_watch(navController)
            }
            composable(Routes.headphone) {
                Headphone(navController)
            }
            composable(Routes.keyboard) {
                Keyboard(navController)
            }
            composable(Routes.mouse) {
                Mouse(navController)
            }
            composable(Routes.speaker) {
                Speaker(navController)
            }
            composable(Routes.cart) {
                Cart()
            }
            composable(Routes.search) {
               Search(navController)
            }
            composable(Routes.admin) {
                home()
            }
            composable(Routes.profile) {
                Profile(navController)
            }
            composable("${Routes.newcode}/{image}/{productName}/{price}/{size}/{description}/{category}",
                arguments = listOf(
                    navArgument("image") { type = NavType.StringType },
                    navArgument("productName") { type = NavType.StringType },
                    navArgument("price") { type = NavType.StringType },
                    navArgument("size") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType },
                    navArgument("category") { type = NavType.StringType }
                )) { backStackEntry ->
                val img = backStackEntry.arguments?.getString("image")
                val productname = backStackEntry.arguments?.getString("productName")
                val price = backStackEntry.arguments?.getString("price")
                val sizeString = backStackEntry.arguments?.getString("size")?:""
                val description = backStackEntry.arguments?.getString("description")
                val Category = backStackEntry.arguments?.getString("category")
                val size = if(sizeString.isNotEmpty()) sizeString.split(",") else emptyList()
                if (img != null && productname != null && price != null && description != null && Category != null) {
                    product(
                        navController,
                        img,
                        productname,
                        price,
                        description,
                        Category,
                        size
                    )
                }
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showBottombar = when (destination.route) {
                Routes.home,
                Routes.search,
                Routes.cart,
                Routes.allCategories -> true
                else -> false
            }
        }

    }
}
@Composable
fun BottomNavigationBar(navController: NavController, navlist: List<bottombar>){
    val currentBackStakEntry by navController.currentBackStackEntryAsState()
    val currentroute=currentBackStakEntry?.destination?.route

    NavigationBar(
        containerColor = Color(16,29,77),
        modifier = Modifier.fillMaxWidth()
    ) {
        navlist.forEach {bottombar->
            NavigationBarItem(
                selected = currentroute==bottombar.routes,
                onClick = {
                    navController.navigate(bottombar.routes){
                        launchSingleTop=true
                    }
                },
                icon = {
                    Image(painter = painterResource(bottombar.image),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        colorFilter = ColorFilter.tint(
                            if(navController.currentBackStackEntryAsState().value?.destination?.route==bottombar.routes) Color.White else Color.Black
                        )
                    )
                },
                label = { Text(bottombar.screenName, fontSize = 15.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}