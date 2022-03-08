package idnull.z.mydiary.ui.analytics_screen

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import idnull.z.mydiary.ui.shared_component.BottomBar
import idnull.z.mydiary.ui.shared_component.FabButton
import idnull.z.mydiary.ui.shared_component.TopBar
import idnull.z.mydiary.ui.theme.PerfectDark

@Composable
fun AnalyticsScreen(navController:NavController) {

    Scaffold(
        backgroundColor = PerfectDark,
        topBar = { TopBar("Analytics",Icons.Outlined.Analytics) },
        floatingActionButton = { FabButton(navController = navController) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(navController = navController) },
    ) {
        Text(text = "Analytics", fontSize = 25.sp, color = Color.White)
    }
}