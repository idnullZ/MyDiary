package idnull.z.mydiary.ui.settings_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.ui.theme.RoyalBlue

@Composable
fun SettingsItem(imageVector: ImageVector, text: String, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = imageVector, contentDescription = text,
                tint = MaterialTheme.colors.onBackground, modifier = Modifier
                    .padding(start = 20.dp, top = 12.dp, bottom = 12.dp)
                    .size(24.dp)
            )
            Text(
                text = text,
                color = MaterialTheme.colors.onBackground,
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