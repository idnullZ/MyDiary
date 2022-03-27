package idnull.z.mydiary.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import idnull.z.mydiary.utils.convertData
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "dairy_table")
data class DiaryUnit(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val date: Long,
    val dateFromCheck :String
) {
    fun toDiaryCalendar(diaryUnit: DiaryUnit) = Diary(
        id = diaryUnit.id,
        title = diaryUnit.title,
        content = diaryUnit.content,
        date = convertToCalendar(diaryUnit.date),
        utilsDate = diaryUnit.dateFromCheck
    )


    private fun convertToCalendar(date: Long): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        return dateFormat.format(date).toString()
    }
}
