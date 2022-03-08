package idnull.z.mydiary.ui.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import idnull.z.mydiary.ui.settings_screen.components.Divorce
import idnull.z.mydiary.ui.settings_screen.components.SettingsItem
import idnull.z.mydiary.ui.settings_screen.components.TextItem
import idnull.z.mydiary.ui.shared_component.BottomBar
import idnull.z.mydiary.ui.shared_component.FabButton
import idnull.z.mydiary.ui.shared_component.TopBar
import idnull.z.mydiary.ui.shared_component.TopText
import idnull.z.mydiary.ui.theme.PerfectDark


@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        backgroundColor = PerfectDark,
        floatingActionButton = { FabButton(navController = navController) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(navController = navController) },
    ) {
        Column {
            TopText(text = "Settings")
            TextItem(text = "PERSONAL")
            SettingsItem(imageVector = Icons.Outlined.DarkMode, "Dark Mode")
            Divorce()
            SettingsItem(imageVector = Icons.Outlined.VpnKey, "Change Code")
            TextItem(text = "REMINDER")
            SettingsItem(imageVector = Icons.Outlined.Notifications, "Daily Reminder")
            TextItem(text = "OTHER")
            SettingsItem(imageVector = Icons.Outlined.HelpCenter, "Help and Feedback")
            Divorce()
            SettingsItem(imageVector = Icons.Outlined.StarBorder, "Rate App")
            Divorce()
            SettingsItem(imageVector = Icons.Outlined.Subject, "About")
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController())
}