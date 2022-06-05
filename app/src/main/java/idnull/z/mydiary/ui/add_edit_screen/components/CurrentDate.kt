package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material.icons.rounded.AddReaction
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.domain.InternalStoragePhoto
import idnull.z.mydiary.ui.shared_component.TextStd
import idnull.z.mydiary.ui.theme.Green
import idnull.z.mydiary.ui.theme.Grey

@Composable
fun CurrentDate(
    images: List<InternalStoragePhoto>,
    date: String,
    showAdapter: Boolean,
    addPhotoClick: () -> Unit,
    addSmilesClick: () -> Unit,
    hideMood: () -> Unit,
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextStd(value = date, fontSize = 18)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
            ) {
                if (images.isNotEmpty()) {
                    Icon(
                        imageVector = if (showAdapter) {
                            Icons.Rounded.KeyboardArrowUp
                        } else {
                            Icons.Rounded.KeyboardArrowDown
                        },
                        contentDescription = "hide",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                            .clickable { hideMood() },
                    )
                }
                Icon(
                    imageVector = Icons.Rounded.AddPhotoAlternate,
                    contentDescription = "add",
                    tint = Green,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(30.dp)
                        .clickable { addPhotoClick() },
                )
                Icon(
                    imageVector = Icons.Rounded.AddReaction,
                    contentDescription = "AddReaction",
                    tint = Green,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(30.dp)
                        .clickable { addSmilesClick() },
                )
            }
        }
        Divider(
            color = Grey,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}