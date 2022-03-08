package idnull.z.mydiary.ui.settings_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.ui.theme.RoyalBlue
import idnull.z.mydiary.ui.theme.Typography

@Composable
fun TextItem(text: String) {
    Box() {
        Text(
            text = text,
            fontFamily = FontFamily.Monospace,
            color = RoyalBlue,
            modifier = Modifier.padding(top = 30.dp, start = 20.dp)
        )
    }
}

@Preview
@Composable
fun tex() {
    TextItem(text = "OTHER")

}