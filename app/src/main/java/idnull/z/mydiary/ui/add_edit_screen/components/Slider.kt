package idnull.z.mydiary.ui.add_edit_screen.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.R
import idnull.z.mydiary.domain.InternalStoragePhoto


@Composable
fun Slider(images: List<InternalStoragePhoto>, closeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        StatusBarManager(deleteClick = {}, closeClick)
        SliderAdapter(images)
    }

}

@Composable
fun StatusBarManager(deleteClick: () -> Unit, closeClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Rounded.Clear,
            contentDescription = "Clear",
            modifier = Modifier
                .clickable { closeClick() }
                .padding(horizontal = 16.dp)
                .size(24.dp),
            tint = Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_trash),
            contentDescription = "Delete button",
            modifier = Modifier
                .clickable { deleteClick() }
                .padding(start = 8.dp, end = 16.dp)
                .size(24.dp),
            tint = Color.Red
        )
    }
}


@Composable
fun SliderAdapter(images: List<InternalStoragePhoto>) {
    LazyRow(modifier = Modifier.fillMaxWidth().background(Color.Green)) {
        items(images) {
            ZoomableImage(image = it)
        }
    }
}

@Composable
fun ImageSlide(image: InternalStoragePhoto) {
    Image(
        bitmap = image.bmp.asImageBitmap(),
        contentDescription = "Image",
        modifier = Modifier.fillMaxHeight().background(Color.Red).size(400.dp),
        contentScale = ContentScale.FillWidth
    )
}







@Composable
fun ZoomableImage(image: InternalStoragePhoto) {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
    Box(
        modifier = Modifier
            .clip(RectangleShape) // Clip the box content
            .fillMaxSize() // Give the size you want...
            .background(Color.Gray)
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    scale.value *= zoom
                    rotationState.value += rotation
                }
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center) // keep the image centralized into the Box
                .graphicsLayer(
                    // adding some zoom limits (min 50%, max 200%)
                    scaleX = maxOf(.5f, minOf(3f, scale.value)),
                    scaleY = maxOf(.5f, minOf(3f, scale.value)),
                    rotationZ = rotationState.value
                ),
            contentDescription = null,
            bitmap = image.bmp.asImageBitmap()
        )
    }
}

