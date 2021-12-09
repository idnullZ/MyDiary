package idnull.z.mydiary.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dairy_table")
data class DiaryUnit(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String,
    val content:String,
    val date:Long


)
