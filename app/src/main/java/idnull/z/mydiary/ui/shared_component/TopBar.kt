package idnull.z.mydiary.ui.shared_component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavController
import idnull.z.mydiary.ui.theme.DarkBar

@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title, color = Color.White, fontStyle = FontStyle.Italic
            )
        },

        backgroundColor = DarkBar,
        elevation = AppBarDefaults.TopAppBarElevation
    )
}