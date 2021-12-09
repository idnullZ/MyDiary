package idnull.z.mydiary.ui.code_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.ui.code_screen.CodeScreenViewModel

@Composable
fun Points(viewModel: CodeScreenViewModel) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Enter your passcode",
            style = TextStyle(color = Color.White),
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 60.dp, bottom = 36.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        ) {
            Point(pointNumber = 0, viewModel = viewModel)
            Point(pointNumber = 1, viewModel = viewModel)
            Point(pointNumber = 2, viewModel = viewModel)
            Point(pointNumber = 3, viewModel = viewModel)
        }
    }
}