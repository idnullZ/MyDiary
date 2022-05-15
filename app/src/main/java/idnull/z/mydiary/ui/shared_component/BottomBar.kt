package idnull.z.mydiary.ui.shared_component

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import idnull.z.mydiary.utils.BottomBarScreen

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarScreen.DairyListScreen,
        BottomBarScreen.CalendarScreen,
        BottomBarScreen.Null,
        BottomBarScreen.AnalyticsScreen,
        BottomBarScreen.SettingsScreen,
    )

    BottomNavigation(
        contentColor = Color.Black
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navController = navController
            )
        }
    }
}