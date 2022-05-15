package idnull.z.mydiary.ui.code_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.ui.code_screen.CodeScreenViewModel

@Composable
fun Points(viewModel: CodeScreenViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Enter your passcode",
            style = TextStyle(color = MaterialTheme.colors.onBackground),
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 60.dp, bottom = 36.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        ) {
            for (i in 0..3) {
                Point(pointNumber = i, viewModel = viewModel)
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}