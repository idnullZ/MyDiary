package idnull.z.mydiary.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import idnull.z.mydiary.domain.themes.AppTheme
import idnull.z.mydiary.domain.themes.ThemeSetting
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Singleton
class ThemeSettingPreference @Inject constructor(
    @ApplicationContext context: Context
) : ThemeSetting {


    override var theme: AppTheme by AppThemePreferenceDelegate("app_theme", AppTheme.MODE_NIGHT)
    private val preferences: SharedPreferences =
        context.getSharedPreferences("sample_theme", Context.MODE_PRIVATE)
    override val themeStream: MutableStateFlow<AppTheme> = MutableStateFlow(theme)


    inner class AppThemePreferenceDelegate(
        private val name: String,
        private val default: AppTheme
    ) : ReadWriteProperty<ThemeSettingPreference, AppTheme> {
        override fun getValue(thisRef: ThemeSettingPreference, property: KProperty<*>): AppTheme =
            AppTheme.fromOrdinal(preferences.getInt(name, default.ordinal))

        override fun setValue(
            thisRef: ThemeSettingPreference,
            property: KProperty<*>,
            value: AppTheme
        ) {
            themeStream.value = value
            preferences.edit {
                putInt(name, value.ordinal)
            }
        }

    }
}













