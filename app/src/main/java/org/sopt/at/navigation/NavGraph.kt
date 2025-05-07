package org.sopt.at.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.at.screen.HistoryScreen
import org.sopt.at.screen.HomeScreen
import org.sopt.at.screen.LiveScreen
import org.sopt.at.screen.MyScreen
import org.sopt.at.screen.SearchScreen
import org.sopt.at.screen.ShortsScreen
import org.sopt.at.screen.SignInScreen
import org.sopt.at.screen.SignUpScreen
import org.sopt.at.viewmodel.AuthViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val authViewModel = remember { AuthViewModel() }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = remember(navBackStackEntry) { navBackStackEntry?.destination?.route }
    val bottomNavRoutes = remember {
        listOf(
            BottomNavItem.Home.route,
            BottomNavItem.Shorts.route,
            BottomNavItem.Live.route,
            BottomNavItem.Search.route,
            BottomNavItem.History.route
        )
    }

    var userIdState by remember { mutableStateOf<Long?>(null) }

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
            startDestination = NavRoute.SignIn,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoute.SignIn) {
                SignInScreen(
                    onSignUpClick = { navController.navigate(NavRoute.SignUp) },
                    onLoginSuccess = { userId ->
                        userIdState = userId
                        userId?.let {
                            authViewModel.fetchNickname(it)
                        }
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo(NavRoute.SignIn) { inclusive = true }
                        }
                    },
                    authViewModel = authViewModel
                )
            }

            composable(NavRoute.SignUp) {
                SignUpScreen(
                    onSignUpSuccess = {
                        navController.navigate(NavRoute.SignIn) {
                            popUpTo(NavRoute.SignUp) { inclusive = true }
                        }
                    },
                    authViewModel = authViewModel
                )
            }

            composable(NavRoute.My) {
                val nickname by authViewModel.nickname.collectAsState()
                if (userIdState != null && nickname.isNotBlank()) {
                    MyScreen(
                        userId = userIdState!!,
                        nickname = nickname,
                        onLogout = {
                            navController.navigate(NavRoute.SignIn) {
                                popUpTo(NavRoute.My) { inclusive = true }
                            }
                        }
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("유저 정보를 불러오는 중입니다...", color = Color.Gray)
                    }
                }
            }



            composable(BottomNavItem.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(BottomNavItem.Shorts.route) { ShortsScreen() }
            composable(BottomNavItem.Live.route) { LiveScreen() }
            composable(BottomNavItem.Search.route) { SearchScreen() }
            composable(BottomNavItem.History.route) { HistoryScreen() }
        }
    }
}
