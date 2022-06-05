package idnull.z.mydiary.ui.add_edit_screen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import idnull.z.mydiary.domain.SmileIcons
import idnull.z.mydiary.ui.shared_component.OutlineRounderButton
import idnull.z.mydiary.ui.shared_component.RounderButton
import idnull.z.mydiary.ui.shared_component.TextStd
import idnull.z.mydiary.ui.theme.DarkBar
import idnull.z.mydiary.utils.changIsSelectSmiles
import idnull.z.mydiary.utils.loger
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@Composable
fun ShowSmileDialog(
    isDialogOpen: MutableState<Boolean>,
    allSmiles: List<SmileIcons>,
    onSaveClick: (smiles: List<SmileIcons>) -> Unit
) {
    var smiles by mutableStateOf(allSmiles)
    val smilesAdapterStata = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    if (isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkBar),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(5.dp))
                    TextStd(value = "How are you?")
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    smilesAdapterStata.scrollToItem(index = 0)
                                }
                            },
                        ) {
                            Image(
                                imageVector = Icons.Default.ArrowBackIos,
                                colorFilter = ColorFilter.lighting(Color.White, Color.White),
                                contentDescription = null
                            )
                        }

                        SmileAdapter(
                            smiles = smiles,
                            state = smilesAdapterStata
                        ) {
                            smiles = changIsSelectSmiles(smiles, it)
                        }

                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    smilesAdapterStata.scrollToItem(index = 14)
                                }
                            },
                        ) {
                            Image(
                                imageVector = Icons.Default.ArrowForwardIos,
                                colorFilter = ColorFilter.lighting(Color.White, Color.White),
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlineRounderButton(
                            text = "Cancel",
                            modifier = Modifier.fillMaxWidth(0.3f)
                        ) { isDialogOpen.value = false }

                        RounderButton(
                            text = "Save",
                            modifier = Modifier.fillMaxWidth(0.6f)
                        ) {
                            onSaveClick(smiles)
                            isDialogOpen.value = false
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}


@Composable
fun Smile(
    icon: SmileIcons,
    isSelectedIgnore: Boolean = false,
    itemClick: (itemName: String) -> Unit
) {

    var color = if (icon.isSelect) Color.White else Color.Transparent
    if (isSelectedIgnore) {
        color = Color.Transparent
    }
    Box(
        Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(color)
    ) {
        Image(
            painter = painterResource(icon.res),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
                .clickable { itemClick(icon.id) }
        )
    }

}

@Composable
fun SmileAdapter(
    smiles: List<SmileIcons>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    isSelectedIgnore: Boolean = false,
    itemClick: (itemName: String) -> Unit = { }
) {
    LazyRow(
        state = state,
        modifier = modifier.fillMaxWidth(0.85f)
    ) {
        items(items = smiles,
            key = { smiles -> smiles.id }) { smile ->
            Smile(smile, isSelectedIgnore, itemClick)
        }
    }
}

