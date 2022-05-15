package idnull.z.mydiary.ui.main


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import idnull.z.mydiary.R
import idnull.z.mydiary.domain.themes.AppTheme
import idnull.z.mydiary.domain.themes.ThemeSetting
import idnull.z.mydiary.ui.add_edit_screen.AddEditScreen
import idnull.z.mydiary.ui.analytics_screen.AnalyticsScreen
import idnull.z.mydiary.ui.calendar_screen.CalendarScreen
import idnull.z.mydiary.ui.code_screen.CodeScreen
import idnull.z.mydiary.ui.diary_list_screen.DiaryListScreen
import idnull.z.mydiary.ui.settings_screen.SettingsScreen
import idnull.z.mydiary.ui.theme.MyDiaryTheme
import idnull.z.mydiary.utils.Screen
import javax.inject.Inject


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var themeSetting: ThemeSetting

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepVisibleCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            val theme = themeSetting.themeStream.collectAsState()
            when (theme.value) {
                AppTheme.MODE_DAY -> {
                    window.statusBarColor =
                        ContextCompat.getColor(this, R.color.white)
                }
                AppTheme.MODE_NIGHT -> {
                    window.statusBarColor =
                        ContextCompat.getColor(this, R.color.black)
                }
            }

            val useDarkColors = when (theme.value) {
                AppTheme.MODE_DAY -> false
                AppTheme.MODE_NIGHT -> true
            }
            if (viewModel.startScreen.isNotEmpty()) {
                Main(useDarkColors, viewModel.startScreen)
                viewModel.finish()
            }


        }
    }
    companion object {
        const val ANIMATION_DURATION = 500
    }
}

















