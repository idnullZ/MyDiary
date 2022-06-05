package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
fun ImageAdapter(images: List<InternalStoragePhoto>, onClick: () -> Unit) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(images) {
            ImageItem(image = it, onClick)
        }
    }
}

@Composable
fun ImageItem(image: InternalStoragePhoto, onClick: () -> Unit) {
    image.bmp?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .clickable { onClick() }
        )
    }
}



