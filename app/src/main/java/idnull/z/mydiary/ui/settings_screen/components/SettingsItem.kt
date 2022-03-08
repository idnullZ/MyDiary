package idnull.z.mydiary.ui.settings_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.ui.theme.RoyalBlue

@Composable
fun SettingsItem(imageVector: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = imageVector, contentDescription = text,
                tint = Color.White, modifier = Modifier
                    .padding(start = 20.dp, top = 12.dp, bottom = 12.dp)
                    .size(32.dp)
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier.padding(start = 21.dp)
            )
        }

        Icon(
            imageVector = Icons.Default.NavigateNext, contentDescription = "NavigateNext",
            tint = RoyalBlue, modifier = Modifier
                .padding(end = 25.dp)
        )
    }


}