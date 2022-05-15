package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.R
import idnull.z.mydiary.ui.shared_component.TextStd
import idnull.z.mydiary.ui.shared_component.TextStdNoPad
import idnull.z.mydiary.ui.theme.Green
import idnull.z.mydiary.ui.theme.PerfectDark

@ExperimentalMaterialApi
@Composable
fun BottomSheet(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    answer: (ChoiceDialog) -> Unit,
    content: @Composable () -> Unit

) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { DialogContent(answer) },
        sheetPeekHeight = 0.dp
    ) { content() }
}

@Composable
fun DialogContent(
    answer: (ChoiceDialog) -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    Box(
        Modifier
            .fillMaxWidth()
            .background(PerfectDark)
    ) {
        Column(

            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .background(MaterialTheme.colors.primary)
            ) {
                DialogItem(R.drawable.ic_galery, "Gallery") {
                    answer(ChoiceDialog.Gallery)
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White)
                )
                DialogItem(R.drawable.ic_camera, "Camera") {
                    answer(ChoiceDialog.Camera)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            TextStd(
                value = "Cancel",
                color = Green,
                fontSize = 18,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .background(MaterialTheme.colors.primary)
            ) {
                answer(ChoiceDialog.Cancel)
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun DialogItem(resource: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(resource),
            contentDescription = null,
            modifier = Modifier
                .padding(21.dp)
        )
        TextStdNoPad(value = text, modifier = Modifier.padding(vertical = 21.dp), fontSize = 18)
    }
}

sealed class ChoiceDialog {
    object Camera : ChoiceDialog()
    object Gallery : ChoiceDialog()
    object Cancel : ChoiceDialog()
}