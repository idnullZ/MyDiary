package idnull.z.mydiary.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object DairyListScreen : BottomBarScreen(
        route = Screen.DairyListScreen.route,
        title = "Diary",
        icon = Icons.Rounded.MenuBook
    )

    object CalendarScreen : BottomBarScreen(
        route = Screen.CalendarScreen.route,
        title = "Calendar",
        icon = Icons.Rounded.DateRange
    )

    object SettingsScreen : BottomBarScreen(
        route = Screen.SettingsScreen.route,
        title = "Settings",
        icon = Icons.Rounded.Settings
    )

    object AnalyticsScreen : BottomBarScreen(
        route = Screen.AnalyticsScreen.route,
        title = "Analytics",
        icon = Icons.Rounded.Analytics
    )

    object Null : BottomBarScreen(
        route = "",
        title = "",
        icon = Icons.Rounded.Done
    )
}