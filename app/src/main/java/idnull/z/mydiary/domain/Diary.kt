package idnull.z.mydiary.domain



data class Diary(
    val id:Int? = null,
    val title:String,
    val content:String,
    val date:String
)


