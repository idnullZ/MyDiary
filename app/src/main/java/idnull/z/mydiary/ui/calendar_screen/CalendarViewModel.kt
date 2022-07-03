package idnull.z.mydiary.ui.calendar_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.NotesCalendar
import idnull.z.mydiary.domain.use_case.GetListDiaryFromCalendarUseCase
import idnull.z.mydiary.utils.dataToLong
import idnull.z.mydiary.utils.loger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getListDiaryFromCalendarUseCase: GetListDiaryFromCalendarUseCase
) : ViewModel() {
    var dataState by mutableStateOf(DataState.LOADING)
        private set
    private var nawData = ""
    private var currentData = 0L


    var diary by mutableStateOf(NotesCalendar())
        private set

    var pickDate by mutableStateOf(0L)
        private set

    private var listDiary = ArrayList<Diary>(listOf())

    fun getDiaryUnit(year: Int, month: Int, day: Int) {
        dataState = DataState.LOADING
        val tryMonth = month + 1
        val dayNaw = if (day < 9) {
            "0$day"
        } else {
            day.toString()
        }
        val format = if (tryMonth < 9) {
            "$year.0$tryMonth.$dayNaw"
        } else {
            "$year.$tryMonth.$dayNaw"
        }

        loger("format = $format")
        searchDiaryUnit(format)
    }

    private fun searchDiaryUnit(data: String) {
        viewModelScope.launch {
            diary = NotesCalendar()
            dataState = DataState.LOADING
            delay(50)
            for (i in listDiary) {
                if (i.date == data) {
                    dataState = DataState.DATA_LOADED
                    diary = NotesCalendar(id = i.id?.toLong(), title = i.title, content = i.content)
                    break
                } else {
                    pickDate = dataToLong(data)

                    dataState = if (currentData < pickDate) {
                        DataState.FUTURE_DATA
                    } else {
                        DataState.DATA_EMPTY
                    }
                }
            }
        }
    }


    init {
        getDiary()
        initSearch()
    }

    private fun initSearch() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                delay(1000)
                searchDiaryUnit(nawData)
                if (nawData.isNotEmpty()) {
                    break
                }
            }
        }
    }

    private fun getDiary() {
        viewModelScope.launch {
            getListDiaryFromCalendarUseCase().collect {
                listDiary = it as ArrayList<Diary>

            }
        }
    }

    fun setData(date: Long) {
        currentData = date
        val format = "yyyy.MM.dd"
        nawData = SimpleDateFormat(format, Locale.getDefault()).format(date).toString()
    }
}