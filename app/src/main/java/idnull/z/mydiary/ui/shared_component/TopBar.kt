package idnull.z.mydiary.ui.shared_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import idnull.z.mydiary.ui.theme.DarkBar
import idnull.z.mydiary.ui.theme.PerfectDark

@Composable
fun TopBar(title: String, imageVector: ImageVector) {
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