package idnull.z.mydiary.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import idnull.z.mydiary.ui.add_edit_screen.AddEditScreen
import idnull.z.mydiary.ui.analytics_screen.AnalyticsScreen
import idnull.z.mydiary.ui.calendar_screen.CalendarScreen
import idnull.z.mydiary.ui.code_screen.CodeScreen
import idnull.z.mydiary.ui.diary_list_screen.DiaryListScreen
import idnull.z.mydiary.ui.settings_screen.SettingsScreen
import idnull.z.mydiary.ui.theme.MyDiaryTheme
import idnull.z.mydiary.utils.Screen

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun Main(useDarkColors: Boolean, startDestination: String) {
    MyDiaryTheme(darkTheme = useDarkColors) {
        Surface(color = MaterialTheme.colors.background)
        {
            val navController = rememberAnimatedNavController()
            AnimatedNavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(
                    Screen.CodeScreen.route,
                    //                            enterTransition = enterAnimatedTransition(Screen.DairyListScreen),
                    //                            exitTransition = exitAnimatedTransition(Screen.DairyListScreen)
                ) {
                    CodeScreen(navController = navController)
                }
                composable(
                    Screen.DairyListScreen.route,
                    //                            enterTransition = enterAnimatedTransition(Screen.AddEditDiaryScreen),
                    //                            exitTransition = exitAnimatedTransition(Screen.AddEditDiaryScreen),
                    //                            popEnterTransition = popEnterAnimatedTransition(Screen.AddEditDiaryScreen),
                    //                            popExitTransition = popExitAnimatedTransition(Screen.AddEditDiaryScreen)
                ) {
                    DiaryListScreen(navController = navController)
                }
                composable(
                    route = Screen.AddEditDiaryScreen.route + "?id={id}",
                    arguments = listOf(
                        navArgument(name = "id") {
                            type = NavType.LongType
                            defaultValue = -1L
                        }
                    ),
                    //                            enterTransition = enterAnimatedTransition(Screen.DairyListScreen),
                    //                            exitTransition = exitAnimatedTransition(Screen.DairyListScreen),
                    //                            popEnterTransition = popEnterAnimatedTransition(Screen.DairyListScreen),
                    //                            popExitTransition = popExitAnimatedTransition(Screen.DairyListScreen)
                ) {
                    AddEditScreen(navController = navController)
                }

                composable(
                    route = Screen.SettingsScreen.route
                ) {
                    SettingsScreen(navController)
                }
                composable(
                    route = Screen.CalendarScreen.route
                ) {
                    CalendarScreen(navController)
                }
                composable(
                    route = Screen.AnalyticsScreen.route
                ) {
                    AnalyticsScreen(navController)
                }
            }

        }
    }
}