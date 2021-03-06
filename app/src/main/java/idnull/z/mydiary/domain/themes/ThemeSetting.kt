package idnull.z.mydiary.domain.themes

import kotlinx.coroutines.flow.StateFlow

enum class AppTheme {
    MODE_DAY,
    MODE_NIGHT;

    companion object {
        fun fromOrdinal(ordinal: Int) = values()[ordinal]
    }
}

interface ThemeSetting {
    val themeStream: StateFlow<AppTheme>
    var theme: AppTheme
}