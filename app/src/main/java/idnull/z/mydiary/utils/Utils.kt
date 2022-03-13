package idnull.z.mydiary.utils

import android.util.Log
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.DiaryUnit
import java.text.SimpleDateFormat
import java.util.*




fun toDiary(diaryUnit: DiaryUnit): Diary {
    return  Diary(
        id = diaryUnit.id,
        title = diaryUnit.title,
        content = diaryUnit.content,
        date = convertData(diaryUnit.date)
    )
}

fun convertData(date: Long): String {
    val dateFormat = SimpleDateFormat("E,dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(date).toString()
}

fun loger(value: String){
    Log.d("TAGMYLOGERTAG","LOG: $value" )
}

