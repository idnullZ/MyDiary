package idnull.z.mydiary.ui.shared_component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextStd(
    value: String,
    modifier: Modifier = Modifier,
    maxLine: Int = 1,
    fontSize: Int = 14
) {
    Text(
        text = value,
        color = Color.White,
        fontSize = fontSize.sp,
        maxLines = maxLine,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.ExtraLight,
        modifier = modifier.padding(16.dp),
        overflow = TextOverflow.Ellipsis
    )
}