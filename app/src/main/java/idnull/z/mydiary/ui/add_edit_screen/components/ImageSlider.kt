package idnull.z.mydiary.ui.add_edit_screen.components

import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ZoomIn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import idnull.z.mydiary.domain.InternalStoragePhoto
import idnull.z.mydiary.ui.shared_component.TextStdNoPad
import idnull.z.mydiary.ui.shared_component.ZoomableImage
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun ImageSlider(
    images: List<InternalStoragePhoto>,
    closeClick: () -> Unit,
    deleteClick: (photo: InternalStoragePhoto) -> Unit
) {
    var isZombie by remember { mutableStateOf(false) }
    var item by remember { mutableStateOf(InternalStoragePhoto()) }
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {
        val state = rememberPagerState(pageCount = images.size)

        TopSection(
            images = images,
            closeClick = closeClick,
            deleteClick = { deleteClick(item) },
            zoomClick = { isZombie = !isZombie }
        )

        HorizontalPager(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) {
            item = images[it]
            Item(images[it].bmp, isZombie)
        }

        if (images.size != 1) {
            BottomSection(size = images.size, index = state.currentPage) {
                if (state.currentPage + 1 < images.size)
                    scope.launch { state.scrollToPage(state.currentPage + 1) }
            }
        }
    }
    BackHandler { closeClick() }

}

@Composable
fun TopSection(
    images: List<InternalStoragePhoto>,
    closeClick: () -> Unit,
    deleteClick: () -> Unit,
    zoomClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { closeClick() }) {
            Icon(Icons.Outlined.KeyboardArrowLeft, null)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            IconButton(
                onClick = { zoomClick() },
                modifier = Modifier.align(CenterStart)
            ) { Icon(Icons.Outlined.ZoomIn, null) }

            TextButton(
                onClick = {
                    if (images.size == 1) closeClick()
                    deleteClick()
                },
                modifier = Modifier.align(CenterEnd)
            ) { TextStdNoPad("Delete") }

        }

    }
}

@Composable
private fun BottomSection(
    size: Int,
    index: Int,
    onNextClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Indicators(size = size, index = index)

        FloatingActionButton(
            onClick = onNextClicked,
            modifier = Modifier.align(CenterEnd),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight, null)
        }
    }
}

@Composable
private fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
private fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
    )
}

@Composable
private fun Item(
    item: Bitmap?,
    isZoomable: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isZoomable) {
            ZoomableImage(image = item)
        } else {
            item?.let { Image(bitmap = it.asImageBitmap(), contentDescription = null) }
        }
    }


}







