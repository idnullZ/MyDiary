package idnull.z.mydiary.ui.calendar_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.use_case.GetListDiaryFromCalendarUseCase
import idnull.z.mydiary.utils.loger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getListDiaryFromCalendarUseCase: GetListDiaryFromCalendarUseCase
) : ViewModel() {

    var diary = mutableStateOf("")
        private set

    private var listDiary = ArrayList<Diary>(listOf())
    //date=2022.03.08

    fun getDiaryUnit(year: Int, month: Int, day: Int) {
        val tryMonth = month + 1
        var dayNaw = ""
        if (day < 9) {
            dayNaw = "0$day"
        } else {
          dayNaw =   day.toString()
        }
        var format = ""
        if (tryMonth < 9) {
            format = "$year.0$tryMonth.$dayNaw"
        } else {
            format = "$year.$tryMonth.$dayNaw"
        }

        searchDiaryUnit(format)
    }

    private fun searchDiaryUnit(data: String) {
        loger(data)
        listDiary.forEach {
            if (it.date == data) {
                loger(it.toString())
                diary.value = it.content

            }
        }
    }


    init {
        getDiary()
    }

    private fun getDiary() {
        viewModelScope.launch {
            getListDiaryFromCalendarUseCase().collect {
                listDiary = it as ArrayList<Diary>

                loger(listDiary.toString())

            }
        }
    }


}