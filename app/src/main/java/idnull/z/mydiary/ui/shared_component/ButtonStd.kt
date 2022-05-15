package idnull.z.mydiary.ui.shared_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ButtonStd(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.padding(all = Dp(10F)),
        border = BorderStroke(
            width = 1.dp,
            brush = SolidColor(Color.White)
        ),
        shape = MaterialTheme.shapes.medium,
    )
    {
        TextStdNoPad(value = text)
    }
}