package idnull.z.mydiary.data.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import idnull.z.mydiary.data.ThemeSettingPreference
import idnull.z.mydiary.domain.themes.ThemeSetting
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SettingModule {

    @Binds
    @Singleton
     fun bindThemeSetting(themeSettingPreference: ThemeSettingPreference): ThemeSetting
}