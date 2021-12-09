package idnull.z.mydiary.domain.use_case

import idnull.z.mydiary.data.DiaryRepository
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.utils.toDiary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetListDiaryUseCase @Inject constructor(
    private val repository: DiaryRepository
) {

    operator fun invoke(): Flow<List<Diary>> {

        return repository.getAllDairy().map { list ->
            list.map {
                toDiary(it)
            }


        }
    }


}



