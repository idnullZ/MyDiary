package idnull.z.mydiary.ui.settings_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SettingsSwitch(
    imageVector: ImageVector,
    text: String,
    switchValue: Boolean,
    onClickSwitch: (emit: Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = imageVector, contentDescription = text,
                tint = MaterialTheme.colors.onBackground, modifier = Modifier
                    .padding(start = 20.dp, top = 12.dp, bottom = 12.dp)
                    .size(24.dp)
            )
            Text(
                text = text,
                color = MaterialTheme.colors.onBackground,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraLight,
                modifier = Modifier.padding(start = 21.dp)
            )
        }
        Row(modifier = Modifier.width(56.dp)) {
            Switch(switch = switchValue, onTap = onClickSwitch)
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}


@Composable
fun Switch(
    switch: Boolean = false,
    scale: Float = 1f,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    checkedTrackColor: Color = Color.Black,
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    thumbColor: Color = Color.White,
    gapBetweenThumbAndTrackEdge: Dp = 1.dp,
    onTap: (emit: Boolean) -> Unit
) {
    val switchON = remember {
        mutableStateOf(switch)
    }

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    val animatePosition = animateFloatAsState(
        targetValue = if (switchON.value)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // This is called when the user taps on the canvas
                        switchON.value = !switchON.value
                        onTap(switchON.value)
                    }
                )
            }
    ) {

        // Track
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx())
        )

        // Thumb
        drawCircle(
            color = thumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )

    }

    Spacer(modifier = Modifier.height(18.dp))
}