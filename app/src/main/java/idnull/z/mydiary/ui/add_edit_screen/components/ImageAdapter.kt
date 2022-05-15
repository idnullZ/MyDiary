package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.domain.InternalStoragePhoto

@Composable
fun ImageAdapter(images: List<InternalStoragePhoto>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(images) {
            ImageItem(image = it)
        }
    }
}

@Composable
fun ImageItem(image: InternalStoragePhoto) {
    Image(
        bitmap = image.bmp.asImageBitmap(),
        contentDescription = "Image",
        modifier = Modifier.size(100.dp)
    )
}



