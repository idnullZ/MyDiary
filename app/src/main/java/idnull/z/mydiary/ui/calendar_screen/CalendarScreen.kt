package idnull.z.mydiary.ui.calendar_screen

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
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
                .background(PerfectDark)
        ) {
            TopText(text = "Calendar")

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
                    .padding(top = 32.dp)
            )

            DiaryContent(viewModel.diary.value)


        }


    }
}