package idnull.z.mydiary.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import idnull.z.mydiary.domain.themes.AppTheme
import idnull.z.mydiary.domain.themes.ThemeSetting
import idnull.z.mydiary.ui.add_edit_screen.AddEditScreen
import idnull.z.mydiary.ui.animate.enterAnimatedTransition
import idnull.z.mydiary.ui.animate.exitAnimatedTransition
import idnull.z.mydiary.ui.animate.popEnterAnimatedTransition
import idnull.z.mydiary.ui.animate.popExitAnimatedTransition
import idnull.z.mydiary.ui.code_screen.CodeScreen
import idnull.z.mydiary.ui.diary_list_screen.DiaryListScreen
import idnull.z.mydiary.ui.theme.MyDiaryTheme
import idnull.z.mydiary.utils.Screen
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themeSetting: ThemeSetting

    @ExperimentalAnimationApi
    @FlowPreview
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme = themeSetting.themeStream.collectAsState()
            val useDarkColors = when (theme.value) {
                AppTheme.MODE_AUTO -> isSystemInDarkTheme()
                AppTheme.MODE_DAY -> false
                AppTheme.MODE_NIGHT -> true
            }
            MyDiaryTheme(darkTheme = useDarkColors) {
                Surface(color = MaterialTheme.colors.background)
                {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.CodeScreen.route
                    ) {
                        composable(
                            Screen.CodeScreen.route,
                            enterTransition = enterAnimatedTransition(Screen.DairyListScreen),
                            exitTransition = exitAnimatedTransition(Screen.DairyListScreen)
                        ) {
                            CodeScreen(navController = navController)
                        }
                        composable(
                            Screen.DairyListScreen.route,
                            enterTransition = enterAnimatedTransition(Screen.AddEditDiaryScreen),
                            exitTransition = exitAnimatedTransition(Screen.AddEditDiaryScreen),
                            popEnterTransition = popEnterAnimatedTransition(Screen.AddEditDiaryScreen),
                            popExitTransition = popExitAnimatedTransition(Screen.AddEditDiaryScreen)
                        ) {
                            DiaryListScreen(navController = navController,
                                onItemSelected = { theme -> themeSetting.theme = theme })
                        }
                        composable(
                            route = Screen.AddEditDiaryScreen.route + "?id={id}",
                            arguments = listOf(
                                navArgument(name = "id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            ),
                            enterTransition = enterAnimatedTransition(Screen.DairyListScreen),
                            exitTransition = exitAnimatedTransition(Screen.DairyListScreen),
                            popEnterTransition = popEnterAnimatedTransition(Screen.DairyListScreen),
                            popExitTransition = popExitAnimatedTransition(Screen.DairyListScreen)
                        ) {
                            AddEditScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val ANIMATION_DURATION = 500
    }
}



















