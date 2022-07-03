package idnull.z.mydiary.ui.shared_component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun OutlineRounderButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        contentPadding = PaddingValues(20.dp, 12.dp),
        border = BorderStroke(2.dp, Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.White
        )
    ) {
        Text(text = text, color = Color.White)
    }
}


@Composable
fun RounderButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        contentPadding = PaddingValues(20.dp, 12.dp),

        ) {
        Text(text = text, color = Color.White)
    }
}