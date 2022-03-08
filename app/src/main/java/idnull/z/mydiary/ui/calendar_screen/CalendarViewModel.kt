package idnull.z.mydiary.ui.calendar_screen

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.utils.convertData
import idnull.z.mydiary.utils.loger
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {
    private val date = Date()
    private val month = SimpleDateFormat("MMMM").format(date)
    private val day = SimpleDateFormat("d").format(date)
    private val year = SimpleDateFormat("YYYY").format(date)



    val numbers = mutableStateListOf("1")

    init {

       test()
    }

    fun test(){
        for (i in 2..31){
            numbers.add(i.toString())
            loger("$i")
        }
    }
}