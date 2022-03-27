package idnull.z.mydiary.ui.calendar_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.ui.shared_component.TextStd


@Composable
fun DiaryContent(text: String) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.White)
            .padding(top = 8.dp)
    )
    Column(modifier = Modifier) {
        TextStd(value = text, maxLine = 5)
    }


}


