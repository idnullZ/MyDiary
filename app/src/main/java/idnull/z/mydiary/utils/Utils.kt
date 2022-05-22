package idnull.z.mydiary.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.DiaryUnit
import java.text.SimpleDateFormat
import java.util.*


const val MIME_TYPE_IMAGE_ALL = "image/*"
const val MIME_TYPE_IMAGE_JPEG = "image/jpeg"
const val MIME_TYPE_IMAGE_PNG = "image/png"
const val MIME_TYPE_IMAGE_JPG = "image/jpg"


fun getGalleryCaptureIntent() =
    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
        type = MIME_TYPE_IMAGE_ALL
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        val mimeTypes = arrayOf(MIME_TYPE_IMAGE_JPEG, MIME_TYPE_IMAGE_PNG, MIME_TYPE_IMAGE_JPG)
        putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
    }

fun checkPermissionImage(context: Context) =
    checkPermissionCamera(context) || checkPermissionStorage(context)

fun checkPermissionCamera(context: Context): Boolean =
    ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

fun checkPermissionStorage(context: Context): Boolean =
    ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

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