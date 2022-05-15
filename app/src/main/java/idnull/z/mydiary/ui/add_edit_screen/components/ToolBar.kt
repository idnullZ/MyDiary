package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import idnull.z.mydiary.R
import idnull.z.mydiary.ui.add_edit_screen.AddEditScreenEvent
import idnull.z.mydiary.ui.add_edit_screen.AddEditViewModel


@Composable
fun ToolBar(
    navController: NavController,
    viewModel: AddEditViewModel,
    saveVisibility: Boolean = false,
    deleteVisibility: Boolean = false

) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface)
    ) {
        Icon(imageVector = Icons.Rounded.Clear, contentDescription = "Clear",

            modifier = Modifier
                .clickable { navController.navigateUp() }
                .padding(horizontal = 16.dp)
                .size(24.dp),
            tint = MaterialTheme.colors.onBackground

        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .height(44.dp)
                .fillMaxWidth()
        ) {
            if (saveVisibility) {
                Icon(imageVector = Icons.Rounded.Done, contentDescription = "save button",
                    modifier = Modifier
                        .clickable {
                            viewModel.obtainEvent(AddEditScreenEvent.SaveDiary)
                        }
                        .padding(horizontal = 8.dp)
                        .size(24.dp),
                    tint = MaterialTheme.colors.onBackground)
            }
            if (deleteVisibility) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = "Delete button",
                    modifier = Modifier
                        .clickable {
                            viewModel.obtainEvent(AddEditScreenEvent.DeleteDiary)
                        }
                        .padding(start = 8.dp, end = 16.dp)
                        .size(24.dp),
                    tint = Color.Red)
            }

        }
    }
}
