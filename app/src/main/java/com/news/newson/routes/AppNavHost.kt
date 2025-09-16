package com.news.newson.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.news.newson.presentation.FlashScreen
import com.news.newson.presentation.HomeScreen
import com.news.newson.presentation.LoginScreen
import com.news.newson.presentation.OnboardingScreen

// NavRoutes.kt
sealed class NavRoutes(val route: String) {
    object Flash : NavRoutes("flash")
    object Onboarding : NavRoutes("onboarding")
    object Login : NavRoutes("login")
    object Home : NavRoutes("home")
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Flash.route
    ) {
        composable(NavRoutes.Flash.route) {
            FlashScreen(
                onNavigate = { isFirstTime ->
                    if (isFirstTime) {
                        navController.navigate(NavRoutes.Onboarding.route) {
                            popUpTo(NavRoutes.Flash.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(NavRoutes.Login.route) {
                            popUpTo(NavRoutes.Flash.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(NavRoutes.Onboarding.route) {
            OnboardingScreen(
                onFinished = {
                    navController.navigate(NavRoutes.Login.route) {
                        popUpTo(NavRoutes.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.Home.route) {
            HomeScreen()
        }
    }
}