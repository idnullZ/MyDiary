package idnull.z.mydiary.data

import androidx.room.*
import idnull.z.mydiary.domain.DiaryUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow


@Dao
interface DiaryDao {

    @Query("SELECT * FROM dairy_table ORDER BY date DESC")
    fun getAllDairy():Flow<List<DiaryUnit>>

    @Query("SELECT * FROM dairy_table WHERE id = :id ")
    suspend fun getDairyById(id:Int):DiaryUnit?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDairy(diaryUnit: DiaryUnit)

    @Query("DELETE FROM dairy_table WHERE id = :id")
    suspend fun deleteDiary(id: Int)

}