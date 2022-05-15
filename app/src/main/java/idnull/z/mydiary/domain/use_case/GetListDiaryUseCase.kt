package idnull.z.mydiary.domain.use_case

import idnull.z.mydiary.data.DiaryRepository
import idnull.z.mydiary.utils.toDiary
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetListDiaryUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    operator fun invoke() = repository.getAllDairy().map { list ->
        list.map { toDiary(it) }
    }
}



