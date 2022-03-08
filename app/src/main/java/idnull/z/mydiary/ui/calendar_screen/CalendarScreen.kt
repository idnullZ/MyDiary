package idnull.z.mydiary.ui.calendar_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddReaction
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import idnull.z.mydiary.ui.calendar_screen.components.Calendar
import idnull.z.mydiary.ui.shared_component.BottomBar
import idnull.z.mydiary.ui.shared_component.FabButton
import idnull.z.mydiary.ui.shared_component.TopBar
import idnull.z.mydiary.ui.shared_component.TopText
import idnull.z.mydiary.ui.theme.PerfectDark
import idnull.z.mydiary.ui.theme.RoyalBlue

@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    Scaffold(
        backgroundColor = PerfectDark,
        floatingActionButton = { FabButton(navController = navController) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(navController = navController) },
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {


            TopText(text = "Calendar")

            Row(
                modifier = Modifier.padding(top = 24.dp, end = 8.dp)
            ) {
                Text(
                    text = "March",
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )

                Icon(
                    Icons.Default.KeyboardArrowDown,
                    "",
                    tint = RoyalBlue,
                    modifier = Modifier.padding(top = 8.dp)
                )

            }

            Calendar(numbers =viewModel.numbers )


        }


    }
}