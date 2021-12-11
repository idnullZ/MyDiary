package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import idnull.z.mydiary.ui.add_edit_screen.AddEditScreenEvent
import idnull.z.mydiary.ui.add_edit_screen.AddEditScreenViewModel


@Composable
fun ToolBar(navController: NavController, viewModel: AddEditScreenViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack",

            modifier = Modifier
                .clickable { navController.navigateUp() }
                .padding(horizontal = 8.dp),
            tint =  MaterialTheme.colors.onBackground

        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .height(44.dp)
                .fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Save, contentDescription = "save button",
                modifier = Modifier
                    .clickable {
                        viewModel.obtainEvent(AddEditScreenEvent.SaveDiary)
                    }
                    .padding(horizontal = 8.dp),
                tint = MaterialTheme.colors.onBackground)



            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete button",
                modifier = Modifier
                    .clickable {
                        viewModel.obtainEvent(AddEditScreenEvent.DeleteDiary)
                    }
                    .padding(horizontal = 8.dp),
                tint = MaterialTheme.colors.onBackground)
        }
    }
}
