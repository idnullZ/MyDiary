package idnull.z.mydiary.ui.shared_component

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.ui.theme.ColorFAB

@Composable
fun FabButton(
    navController: NavController,
    viewModel: ComposeViewModel = hiltViewModel(),
) {
    FloatingActionButton(
        backgroundColor = ColorFAB,
        onClick = { viewModel.navigateToAddEditScreen(navController) }
    ) { Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White) }
}