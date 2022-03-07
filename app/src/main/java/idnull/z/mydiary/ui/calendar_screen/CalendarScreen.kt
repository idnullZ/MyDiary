package idnull.z.mydiary.ui.calendar_screen

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import idnull.z.mydiary.ui.shared_component.BottomBar
import idnull.z.mydiary.ui.shared_component.FabButton
import idnull.z.mydiary.ui.shared_component.TopBar
import idnull.z.mydiary.ui.theme.PerfectDark

@Composable
fun CalendarScreen(
    navController: NavController
) {
    Scaffold(
        backgroundColor = PerfectDark,
        topBar = { TopBar("Calendar", navController) },
        floatingActionButton = { FabButton(navController = navController) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(navController = navController) },
    ) {
        Text(text = "Calendar", fontSize = 25.sp, color = Color.White)
    }
}