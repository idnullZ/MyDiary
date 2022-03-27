package idnull.z.mydiary.ui.shared_component

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import idnull.z.mydiary.data.TemporaryStorage
import idnull.z.mydiary.ui.theme.ColorFAB
import idnull.z.mydiary.utils.Screen

@Composable
fun FabButton(navController:NavController) {




    FloatingActionButton(
        backgroundColor = ColorFAB,
        onClick = {
            navController.navigate(Screen.AddEditDiaryScreen.route+"?id=${TemporaryStorage.id}")
        }
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add" , tint = Color.White)
    }
}