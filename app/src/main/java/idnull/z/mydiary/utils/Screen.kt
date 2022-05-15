package idnull.z.mydiary.utils

sealed class Screen(val route: String) {
    object AnalyticsScreen : Screen("Analytics")
    object CodeScreen : Screen("codeScreen")
    object DairyListScreen : Screen("diaryListScreen")
    object AddEditDiaryScreen : Screen("addEditScreen")
    object CalendarScreen : Screen("CalendarScreen")
    object SettingsScreen : Screen("SettingsScreen")
}
