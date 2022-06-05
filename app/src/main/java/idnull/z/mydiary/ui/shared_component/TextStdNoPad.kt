package idnull.z.mydiary.ui.shared_component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextStdNoPad(
    text: String,
    modifier: Modifier = Modifier,
    maxLine: Int = 1,
    fontSize: Int = 14,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = fontSize.sp,
        maxLines = maxLine,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.ExtraLight,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
    )
}