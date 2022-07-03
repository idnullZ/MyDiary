package idnull.z.mydiary.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.StrictMode
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat
import idnull.z.mydiary.data.InternalStorageRepository.Companion.SEPARATOR
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.DiaryUnit
import idnull.z.mydiary.domain.SmileIcons
import idnull.z.mydiary.ui.add_edit_screen.AddEditUtils
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.sqrt


const val MIME_TYPE_IMAGE_ALL = "image/*"
const val MIME_TYPE_IMAGE_JPEG = "image/jpeg"
const val MIME_TYPE_IMAGE_PNG = "image/png"
const val MIME_TYPE_IMAGE_JPG = "image/jpg"

const val SIMPLE_DATE_FORMAT_TWO = "-mm -ss"
const val FORMAT_EXTENSION_JPG = ".jpg"
private const val SD_CARD_PATH = "/sdcard/"

fun getImageWithName(name: String): Bitmap = BitmapFactory.decodeFile(name, BitmapFactory.Options())
val mediaPermissions = listOf(
    Manifest.permission.CAMERA,
    Manifest.permission.READ_EXTERNAL_STORAGE
)

fun getCameraCaptureWithNameIntent(): Pair<Intent, String> {
    val builder = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(builder.build())
    val intent = Intent()
    intent.action = MediaStore.ACTION_IMAGE_CAPTURE
    val newPicFile = SimpleDateFormat(SIMPLE_DATE_FORMAT_TWO, Locale.getDefault()).format(Date())
        .toString() + FORMAT_EXTENSION_JPG
    val outFile = File("$SD_CARD_PATH$newPicFile")
    val outUri = Uri.fromFile(outFile)

    return intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri) to outFile.toString()
}

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


fun String.removeLast4() = this.substring(0, this.length - 4)


fun toDiary(diaryUnit: DiaryUnit) = Diary(
    id = diaryUnit.id,
    title = diaryUnit.title,
    content = diaryUnit.content,
    date = convertData(diaryUnit.date),
    utilsDate = diaryUnit.dateFromCheck
)



@SuppressLint("SimpleDateFormat")
fun dataToLong(data: String): Long {
    val f = SimpleDateFormat("yyyy.MM.dd")
    val d: Date = f.parse(data) as Date
    return d.time
}

fun convertData(date: Long) =
    SimpleDateFormat("E,dd MMM yyyy", Locale.getDefault()).format(date).toString()

fun convertToData(data: Long) =
    SimpleDateFormat("d.MMM.yyyy", Locale.getDefault()).format(data).toString()

fun convertDataFullInfo(date: Long) =
    SimpleDateFormat("E,MMMd h:mm a", Locale.getDefault()).format(date).toString()

fun loger(value: Any = "work") {
    Log.d("TAGMySuperTAG", "LOG: $value")
}

fun changIsSelectSmiles(smiles: List<SmileIcons>, id: String): List<SmileIcons> {
    val result = smiles.toMutableList()
    for (i in smiles.indices) {
        if (smiles[i].id == id) {
            result[i] = result[i].copy(isSelect = !smiles[i].isSelect)
        }
    }
    return result
}

fun convertSmilesToString(smiles: List<SmileIcons>): String {
    val result = mutableListOf<String>()
    for (i in smiles) {
        result.add(i.id)
    }
    return result.joinToString(SEPARATOR)
}

fun convertStringToSmiles(string: String): List<SmileIcons> {
    val smiles = AddEditUtils().smiles.toMutableList()
    for (i in smiles.indices) {
        if (string.contains(smiles[i].id)) {
            smiles[i] = smiles[i].copy(isSelect = true)
        }
    }

    return smiles
}