package idnull.z.mydiary.ui.code_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ItemNumber(number: String, onClick: (number: String) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (number != " ") {
                    onClick(number)
                }
            }
            .padding(
                start = 16.dp, end = 16.dp, top = 12.dp, bottom = 24.dp
            )
    )
    {
        Text(
            text = number,
            style = TextStyle(color = Color.Red),
            fontSize = 40.sp
        )

    }
}