package idnull.z.mydiary.ui.code_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.ui.code_screen.CodeScreenViewModel

@Composable
fun Point(pointNumber: Int, viewModel: CodeScreenViewModel) {

    Box(
        modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(
                if (pointNumber < viewModel.countItemPassword.value) {
                    Color.Red
                } else {
                    Color.White

                }
            )
    )

}