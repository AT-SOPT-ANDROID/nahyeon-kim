package org.sopt.at.navigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.at.R

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    object Home : BottomNavItem("home", R.drawable.ic_home, "HOME")
    object Shorts : BottomNavItem("shorts", R.drawable.ic_shorts, "Shorts")
    object Live : BottomNavItem("live", R.drawable.ic_live, "LIVE")
    object Search : BottomNavItem("search", R.drawable.ic_search, "Search")
    object History : BottomNavItem("history", R.drawable.ic_history, "HISTORY")
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Shorts,
        BottomNavItem.Live,
        BottomNavItem.Search,
        BottomNavItem.History
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.Black, tonalElevation = 4.dp
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route

            NavigationBarItem(
                selected = selected, onClick = {
                if (currentRoute != item.route) {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        tint = if (selected) Color.White else Color.Gray
                    )
                    Text(
                        text = item.label, color = if (selected) Color.White else Color.Gray
                    )
                }
            }, colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Black
            ), interactionSource = remember { MutableInteractionSource() }, // 👈 ripple 제거용
                alwaysShowLabel = true
            )
        }
    }
}
