package idnull.z.mydiary.ui.shared_component

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import idnull.z.mydiary.ui.theme.PerfectDark

@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Monospace,
            )
        },

        backgroundColor = PerfectDark,
        elevation = AppBarDefaults.TopAppBarElevation
    )
}