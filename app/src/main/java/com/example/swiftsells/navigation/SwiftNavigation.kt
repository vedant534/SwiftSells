package com.example.swiftsells.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swiftsells.screens.addscreen.AddScreen
import com.example.swiftsells.screens.homescreen.HomeScreen
import com.example.swiftsells.screens.searchscreen.SearchScreen
import com.example.swiftsells.screens.splashscreen.SplashScreen

@Composable
fun SwiftNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SwiftScreens.SplashScreen.name
    ) {
        composable(SwiftScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(SwiftScreens.HomeScreen.name) {
            HomeScreen(
                navController = navController
            )
        }

        composable(SwiftScreens.AddScreen.name) {
            AddScreen(navController = navController)
        }

        composable(SwiftScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

    }
}