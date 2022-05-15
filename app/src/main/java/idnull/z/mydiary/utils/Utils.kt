package idnull.z.mydiary.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.DiaryUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

fun createBitmapFromUri(uri: Uri?, context: Context): Bitmap? =
    if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } else {
        requireNotNull(uri)
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    }


fun openSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri: Uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}


fun String.removeLast4(): String {
    return this.substring(0, this.length - 4)
}

fun toDiary(diaryUnit: DiaryUnit) = Diary(
    id = diaryUnit.id,
    title = diaryUnit.title,
    content = diaryUnit.content,
    date = convertData(diaryUnit.date),
    utilsDate = diaryUnit.dateFromCheck
)

fun convertData(date: Long) =
    SimpleDateFormat("E,dd MMM yyyy", Locale.getDefault()).format(date).toString()

fun convertToData(data: Long) =
    SimpleDateFormat("d.MMM.yyyy", Locale.getDefault()).format(data).toString()

fun convertDataFullInfo(date: Long) =
    SimpleDateFormat("E,MMMd h:mm a", Locale.getDefault()).format(date).toString()

fun loger(value: Any = "work") {
    Log.d("TAGMYLOGERTAG", "LOG: $value")
}

