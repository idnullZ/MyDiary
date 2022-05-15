package idnull.z.mydiary.utils

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.StrictMode
import android.provider.MediaStore
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val UPLOADED_GALLERY_IMAGE_NAME = "galleryImage"
const val MIME_TYPE_IMAGE_ALL = "image/*"
const val MIME_TYPE_IMAGE_JPEG = "image/jpeg"
const val MIME_TYPE_IMAGE_PNG = "image/png"
const val MIME_TYPE_IMAGE_JPG = "image/jpg"
const val SIMPLE_DATE_FORMAT_TWO = "-mm-ss"
const val FORMAT_EXTENSION_JPG = ".jpg"
const val SD_CARD_PATH = "/sdcard/"

private const val ROTATE_ANGLE_90 = 90f
private const val ROTATE_ANGLE_180 = 180f
private const val ROTATE_ANGLE_270 = 270f


private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(source, 0, 0, source.width, source.height,
        matrix, true)
}

fun getRotateImage(photoPath: String, bitmap: Bitmap): Bitmap {
    val exifInterface = ExifInterface(photoPath)
    val orientation: Int = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED)
    return when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, ROTATE_ANGLE_90)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, ROTATE_ANGLE_180)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, ROTATE_ANGLE_270)
        ExifInterface.ORIENTATION_NORMAL -> bitmap
        else -> bitmap
    }
}

fun getGalleryCaptureIntent() =
    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
        type = MIME_TYPE_IMAGE_ALL
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        val mimeTypes = arrayOf(MIME_TYPE_IMAGE_JPEG, MIME_TYPE_IMAGE_PNG, MIME_TYPE_IMAGE_JPG)
        putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
    }


fun getCameraCaptureIntentWithName(): Pair<Intent, String> {
    val builder = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(builder.build())
    val intent = Intent()
    intent.action = MediaStore.ACTION_IMAGE_CAPTURE

    val date = Date()
    val dateFormat: DateFormat = SimpleDateFormat(SIMPLE_DATE_FORMAT_TWO, Locale.getDefault())

    val newPicFile: String = dateFormat.format(date).toString() + FORMAT_EXTENSION_JPG
    val outPath = "$SD_CARD_PATH$newPicFile"
    val outFile = File(outPath)
    val outUri = Uri.fromFile(outFile)

    return intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri) to outFile.toString()
}