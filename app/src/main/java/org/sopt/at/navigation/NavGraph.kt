package org.sopt.at.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.screen.SignInScreen
import org.sopt.at.screen.SignUpScreen
import org.sopt.at.MyScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "signIn",
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
        popEnterTransition = { fadeIn(animationSpec = tween(700)) },
        popExitTransition = { fadeOut(animationSpec = tween(700)) }
    ) {
        composable("signIn") {
            SignInScreen(
                onSignUpClick = { navController.navigate("signUp") },
                onLoginSuccess = { navController.navigate("my") {
                    popUpTo("signIn") { inclusive = true }
                } }
            )
        }

        composable("signUp") {
            SignUpScreen(onSignUpSuccess = {
                navController.navigate("signIn") {
                    popUpTo("signUp") { inclusive = true }
                }
            })
        }

        composable("my") {
            MyScreen(
                userId = "사용자",
                onLogout = {
                    navController.navigate("signIn") {
                        popUpTo("my") { inclusive = true }
                    }
                }
            )
        }
    }
}
