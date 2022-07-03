package idnull.z.mydiary.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import idnull.z.mydiary.domain.DiaryUnit
import kotlinx.coroutines.flow.Flow


@Dao
interface DiaryDao {

    @Query("SELECT * FROM dairy_table ORDER BY date DESC")
    fun getAllDairy(): Flow<List<DiaryUnit>>

    @Query("SELECT * FROM dairy_table WHERE id = :id ")
    suspend fun getDairyById(id: Int): DiaryUnit?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDairy(diaryUnit: DiaryUnit)

    @Query("DELETE FROM dairy_table WHERE id = :id")
    suspend fun deleteDiary(id: Int)
}