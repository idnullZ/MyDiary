package idnull.z.mydiary.domain.use_case

import idnull.z.mydiary.data.DiaryRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetListDiaryFromCalendarUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    operator fun invoke() = repository.getAllDairy().map { list ->
        list.map { it.toDiaryCalendar(it) }
    }
}