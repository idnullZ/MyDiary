package idnull.z.mydiary.data

import idnull.z.mydiary.domain.DiaryUnit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiaryRepository @Inject constructor(
    private val dao: DiaryDao
) {
    fun getAllDairy(): Flow<List<DiaryUnit>> = dao.getAllDairy()

    suspend fun getDairyById(id: Int) = dao.getDairyById(id = id)

    suspend fun insertDiary(diaryUnit: DiaryUnit) =
        dao.insertDairy(diaryUnit = diaryUnit)

    suspend fun deleteDiary(id: Int) =
        dao.deleteDiary(id)
}