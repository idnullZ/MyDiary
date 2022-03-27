package idnull.z.mydiary.ui.settings_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.domain.themes.AppTheme
import idnull.z.mydiary.ui.settings_screen.components.Divorce
import idnull.z.mydiary.ui.settings_screen.components.SettingsItem
import idnull.z.mydiary.ui.settings_screen.components.SettingsSwitch
import idnull.z.mydiary.ui.settings_screen.components.TextItem
import idnull.z.mydiary.ui.shared_component.BottomBar
import idnull.z.mydiary.ui.shared_component.FabButton
import idnull.z.mydiary.ui.shared_component.TopText
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        floatingActionButton = { FabButton(navController = navController) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(navController = navController) },
    ) {
        Column {
            TopText(text = "Settings")
            TextItem(text = "PERSONAL")
            SettingsSwitch(
                imageVector = Icons.Outlined.DarkMode,
                "Dark Mode",
                switchValue = viewModel.darkMode.value
            ) { viewModel.obtainEvent(SettingsScreenEvent.DarkModeChange(it)) }
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

//    LaunchedEffect(key1 = viewModel.actionFlow) {
//        viewModel.actionFlow.collectLatest {
//            when (it) {
//
//            }
//        }
//    }
}

