package idnull.z.mydiary.ui.calendar_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.ui.theme.Shapes
import idnull.z.mydiary.ui.theme.Yellow


@Composable
fun DiaryContent(text:String) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.White)
            .padding(top = 8.dp)
    )
    Column(modifier = Modifier) {

        Card(
            modifier = Modifier.padding(16.dp).fillMaxWidth().height(100.dp),
            elevation = 8.dp,
            shape = Shapes.medium,
            backgroundColor = Yellow
        ) {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraLight,
                maxLines = 5,
                modifier = Modifier.padding(16.dp),
                overflow = TextOverflow.Ellipsis
            )

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )


    }


}