package idnull.z.mydiary.data

import androidx.room.Database
import androidx.room.RoomDatabase
import idnull.z.mydiary.domain.DiaryUnit

@Database(entities = [DiaryUnit::class], version = 1, exportSchema = false)
abstract class DiaryDataBase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
}