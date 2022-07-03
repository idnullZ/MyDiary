package idnull.z.mydiary.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import idnull.z.mydiary.domain.InternalStoragePhoto
import idnull.z.mydiary.utils.FORMAT_EXTENSION_JPG
import idnull.z.mydiary.utils.removeLast4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*
import javax.inject.Inject

class InternalStorageRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val name get() = Calendar.getInstance().timeInMillis.toString()

    fun saveImages(images: List<InternalStoragePhoto>): String {
        val names = mutableListOf<String>()
        if (images.isEmpty()) return ""

        for (image in images) {
            names.add(name)
            if (!savePhotoToInternalStorage(filename = name, bmp = image.bmp!!)) {
                throw IOException("Couldn't save bitmap.")
            }
        }
        return names.joinToString(SEPARATOR) + SEPARATOR
    }

    suspend fun getAllPhotos(photoString: String): List<InternalStoragePhoto> {
        if (photoString.isEmpty()) return emptyList()
        var start = 0
        val result = mutableListOf<String>()
        for (i in photoString.indices) {
            if (photoString[i] == '#') {
                result.add(photoString.substring(start, i))
                val value = i + 1
                start = value
            }
        }
        return loadPhotosFromInternalStorage(result)

    }

    fun deleteAllPhoto(images: List<InternalStoragePhoto>): Result<Unit> {
        if (images.isEmpty()) return Result.success(Unit)

        for (i in images) {
            val result = deletePhotoFromInternalStorage(i.name)
            if (result.isFailure) {
                return result
            }
        }
        return Result.success(Unit)
    }

    private fun deletePhotoFromInternalStorage(filename: String) = try {
        context.deleteFile(filename)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }


    private suspend fun loadPhotosFromInternalStorage(names: List<String>): List<InternalStoragePhoto> {
        return withContext(Dispatchers.IO) {
            val files = context.filesDir.listFiles()
            files?.filter {
                it.canRead() && it.isFile && it.name.endsWith(FORMAT_EXTENSION_JPG)
                        && names.contains(it.name.removeLast4())
            }?.map {
                val bytes = it.readBytes()
                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                InternalStoragePhoto(bmp, it.name)
            } ?: listOf()
        }
    }

    private fun savePhotoToInternalStorage(filename: String, bmp: Bitmap) = try {
        context.openFileOutput(filename + FORMAT_EXTENSION_JPG, AppCompatActivity.MODE_PRIVATE)
            .use { stream ->
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 85, stream)) {
                    throw IOException("Couldn't save bitmap.")
                }
            }
        true
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }

    companion object {
        const val SEPARATOR = "#"
    }
}
