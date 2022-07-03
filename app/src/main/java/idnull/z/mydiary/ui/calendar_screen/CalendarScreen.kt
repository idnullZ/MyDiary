package idnull.z.mydiary.ui.calendar_screen

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.ui.calendar_screen.components.DiaryContent
import idnull.z.mydiary.ui.shared_component.BottomBar
import idnull.z.mydiary.ui.shared_component.FabButton
import idnull.z.mydiary.ui.shared_component.TopText
import idnull.z.mydiary.ui.theme.PerfectDark
import idnull.z.mydiary.utils.Screen


@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = PerfectDark,
        floatingActionButton = { FabButton(navController = navController) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(navController = navController) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(PerfectDark)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                TopText(text = "Calendar")
                viewModel.diary.id?.let {
                    Icon(
                        imageVector = Icons.Rounded.EditNote,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .clickable {
                                navController.navigate(Screen.AddEditDiaryScreen.route + "?id=$it")
                            }
                    )

                }
            }

            AndroidView(
                factory = { CalendarView(it) },
                update = {
                    it.scaleX = 1.1f
                    it.scaleY = 1.1f
                    viewModel.setData(it.date)
                    it.setOnDateChangeListener { _, year, month, dayOfMonth ->
                        viewModel.getDiaryUnit(year = year, month = month, day = dayOfMonth)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )
            DiaryContent(viewModel.diary, viewModel.dataState, viewModel.pickDate, navController)
        }
    }
}