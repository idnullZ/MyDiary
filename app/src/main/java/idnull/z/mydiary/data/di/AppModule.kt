package idnull.z.mydiary.data.di


import android.content.Context
import androidx.navigation.NavController
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import idnull.z.mydiary.data.DiaryDataBase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDiaryDataBase(@ApplicationContext app: Context): DiaryDataBase {
        return Room.databaseBuilder(
            app,
            DiaryDataBase::class.java,
            "diary_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db: DiaryDataBase) = db.diaryDao()
}