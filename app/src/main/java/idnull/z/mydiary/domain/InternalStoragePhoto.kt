package idnull.z.mydiary.domain

import android.graphics.Bitmap

data class InternalStoragePhoto(
    val bmp: Bitmap? = null,
    val name: String = "",
)