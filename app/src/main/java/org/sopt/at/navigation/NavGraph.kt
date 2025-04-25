package org.sopt.at.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.at.MyScreen
import org.sopt.at.screen.HistoryScreen
import org.sopt.at.screen.HomeScreen
import org.sopt.at.screen.LiveScreen
import org.sopt.at.screen.SignInScreen
import org.sopt.at.screen.SignUpScreen
import org.sopt.at.screen.SearchScreen
import org.sopt.at.screen.ShortsScreen
import org.sopt.at.viewmodel.AuthViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val authViewModel = remember { AuthViewModel() }


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomNavRoutes = listOf(
        BottomNavItem.Home.route,
        BottomNavItem.Shorts.route,
        BottomNavItem.Live.route,
        BottomNavItem.Search.route,
        BottomNavItem.History.route
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavRoutes) {
                BottomNavBar(navController)
            }
        },
        containerColor = Color.Black
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "signIn",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("signIn") {
                SignInScreen(
                    onSignUpClick = { navController.navigate("signUp") },
                    onLoginSuccess = {
                        navController.navigate("home") {
                            popUpTo("signIn") { inclusive = true }
                        }
                    },
                    authViewModel = authViewModel
                )
            }

            composable("signUp") {
                SignUpScreen(
                    onSignUpSuccess = {
                        navController.navigate("signIn") {
                            popUpTo("signUp") { inclusive = true }
                        }
                    },
                    authViewModel = authViewModel
                )
            }

            composable("my") {
                MyScreen(
                    userId = authViewModel.registeredId.value,
                    onLogout = {
                        navController.navigate("signIn") {
                            popUpTo("my") { inclusive = true }
                        }
                    }
                )
            }


            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }


            composable(BottomNavItem.Shorts.route) { ShortsScreen() }
            composable(BottomNavItem.Live.route) { LiveScreen() }
            composable(BottomNavItem.Search.route) { SearchScreen() }
            composable(BottomNavItem.History.route) { HistoryScreen() }
        }
    }
}
