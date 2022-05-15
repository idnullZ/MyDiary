package idnull.z.mydiary.ui.calendar_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.use_case.GetListDiaryFromCalendarUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getListDiaryFromCalendarUseCase: GetListDiaryFromCalendarUseCase
) : ViewModel() {

    private var nawData = ""

    var diary = mutableStateOf("")
        private set

    val date = mutableStateOf("")

    private var listDiary = ArrayList<Diary>(listOf())

    fun getDiaryUnit(year: Int, month: Int, day: Int) {
        val tryMonth = month + 1
        var dayNaw = ""
        dayNaw = if (day < 9) {
            "0$day"
        } else {
            day.toString()
        }
        var format = ""
        format = if (tryMonth < 9) {
            "$year.0$tryMonth.$dayNaw"
        } else {
            "$year.$tryMonth.$dayNaw"
        }

        searchDiaryUnit(format)
    }

    private fun searchDiaryUnit(data: String) {
        diary.value = ""
        listDiary.forEach {
            if (it.date == data) {
                diary.value = it.content

            }
        }
    }


    init {
        getDiary()
        tupoFun()
    }

    private fun tupoFun() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true){
                delay(1000)
                searchDiaryUnit(nawData)
                if (nawData.isNotEmpty()){
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
        val format = "yyyy.MM.dd"
        nawData = SimpleDateFormat(format, Locale.getDefault()).format(date).toString()
    }

}