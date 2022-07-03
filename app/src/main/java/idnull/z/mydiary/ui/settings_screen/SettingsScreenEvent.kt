package idnull.z.mydiary.ui.settings_screen

sealed class SettingsScreenEvent{
    data class DarkModeChange(val value: Boolean):SettingsScreenEvent()
}
