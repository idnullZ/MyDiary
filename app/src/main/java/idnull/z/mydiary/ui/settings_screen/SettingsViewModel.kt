package idnull.z.mydiary.ui.settings_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.domain.themes.AppTheme
import idnull.z.mydiary.domain.themes.ThemeSetting
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val themeSetting: ThemeSetting) : ViewModel() {

    private val theme = themeSetting.themeStream

    val darkMode = mutableStateOf(false)

    private val _actionFlow = MutableSharedFlow<SettingsScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()


    fun obtainEvent(event: SettingsScreenEvent) {
        when (event) {
            is SettingsScreenEvent.DarkModeChange -> {
                viewModelScope.launch {
                    val mode = if (event.value) AppTheme.MODE_NIGHT else AppTheme.MODE_DAY
                    themeSetting.theme = mode
                }
            }
        }

    }


   private fun initDarkMode() {
        val useDarkMode = when (theme.value) {
            AppTheme.MODE_DAY -> false
            AppTheme.MODE_NIGHT -> true
        }
        darkMode.value = useDarkMode
    }

    init {
        initDarkMode()
    }
}