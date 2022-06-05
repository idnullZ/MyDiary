package idnull.z.mydiary.ui.shared_component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun ZoomableImage(image: Bitmap?) {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
    Box(
        modifier = Modifier
            .clip(RectangleShape) // Clip the box content
            .fillMaxSize() // Give the size you want...
            .background(Color.Gray)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, rotation ->
                    scale.value *= zoom
                    rotationState.value += rotation
                }
            }
    ) {
        image?.let {
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
                bitmap = it.asImageBitmap()
            )
        }
    }
}