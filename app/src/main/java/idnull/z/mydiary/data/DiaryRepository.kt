package idnull.z.mydiary.data

import idnull.z.mydiary.domain.DiaryUnit
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DiaryRepository @Inject constructor(
    private val dao: DiaryDao
) {
    fun getAllDairy():Flow<List<DiaryUnit>>{
        return dao.getAllDairy()
    }

    suspend fun getDairyById(id:Int):DiaryUnit?{
       return dao.getDairyById(id = id)
    }

    suspend fun insertDiary(diaryUnit: DiaryUnit){
        dao.insertDairy(diaryUnit = diaryUnit)
    }

    suspend fun deleteDiary(id:Int){
        dao.deleteDiary(id)
    }



}