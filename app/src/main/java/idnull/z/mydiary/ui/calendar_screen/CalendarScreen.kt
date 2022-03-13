package idnull.z.mydiary.ui.calendar_screen

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.ui.calendar_screen.components.DiaryContent
import idnull.z.mydiary.ui.shared_component.BottomBar
import idnull.z.mydiary.ui.shared_component.FabButton
import idnull.z.mydiary.ui.shared_component.TopText
import idnull.z.mydiary.ui.theme.CalendarBlue
import idnull.z.mydiary.ui.theme.PerfectDark
import idnull.z.mydiary.utils.loger


@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = hiltViewModel(),
) {
    var data by remember { mutableStateOf("") }

    Scaffold(
        backgroundColor = PerfectDark,
        floatingActionButton = { FabButton(navController = navController) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(navController = navController) },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CalendarBlue)
        ) {
            TopText(text = "Calendar")

            AndroidView(
                factory = { CalendarView(it) },
                update = {
                    it.setOnDateChangeListener { _, year, month, dayOfMonth ->
                        viewModel.getDiaryUnit(year = year, month = month, day = dayOfMonth)
                        loger(" $year, $month, $dayOfMonth ")
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            DiaryContent(viewModel.diary.value)


        }


    }
}