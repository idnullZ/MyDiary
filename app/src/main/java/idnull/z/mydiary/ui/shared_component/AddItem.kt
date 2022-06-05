package idnull.z.mydiary.ui.shared_component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import idnull.z.mydiary.ui.theme.DarkBar
import idnull.z.mydiary.utils.BottomBarScreen
import idnull.z.mydiary.utils.Screen


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navController: NavController
) {
    BottomNavigationItem(
        modifier = Modifier.background(DarkBar),
        label = {
            Text(text = screen.title, color = Color.White, fontSize = 10.sp)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                tint = Color.White,
                contentDescription = "Navigation Icon"
            )
        },
        selected = navController.currentDestination?.route == screen.route,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        selectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.high),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(Screen.DairyListScreen.route) { inclusive = false }
            }
        }
    )
}