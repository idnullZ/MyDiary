package idnull.z.mydiary.ui.calendar_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import idnull.z.mydiary.domain.NotesCalendar
import idnull.z.mydiary.ui.calendar_screen.DataState
import idnull.z.mydiary.ui.shared_component.ExpandingText
import idnull.z.mydiary.ui.shared_component.OutlineRounderButton
import idnull.z.mydiary.ui.shared_component.TextStd
import idnull.z.mydiary.utils.Screen
import idnull.z.mydiary.utils.convertData


@Composable
fun DiaryContent(
    diary: NotesCalendar,
    dataState: DataState,
    pickData: Long,
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = Color.White)
        when (dataState) {
            DataState.LOADING -> {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                )
            }
            DataState.DATA_LOADED -> {
                if (!diary.title.isNullOrEmpty()) {
                    ExpandingText(
                        longText = diary.title.orEmpty(),
                        textColor = Color.White,
                        fontSize = 16,
                        minimizedMaxLines = 1,
                        moreLessTextColor = Color.Gray,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                ExpandingText(
                    longText = diary.content.orEmpty(),
                    textColor = Color.White,
                    fontSize = 12,
                    minimizedMaxLines = 1,
                    moreLessTextColor = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }

            DataState.DATA_EMPTY -> {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )

                OutlineRounderButton(text = "Create on ${convertData(pickData)}") {
                    navController.navigate(Screen.AddEditDiaryScreen.route + "?id=$pickData")
                }
            }
            DataState.FUTURE_DATA -> {
                TextStd(value = "Nothing here yet", fontSize = 22)
            }

        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}


