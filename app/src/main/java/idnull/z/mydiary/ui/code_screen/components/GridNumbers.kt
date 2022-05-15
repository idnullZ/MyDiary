package idnull.z.mydiary.ui.code_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.ui.code_screen.CodeScreenViewModel

@ExperimentalFoundationApi
@Composable
fun GridNumbers(numbers: List<String>, viewModel: CodeScreenViewModel) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 3),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        items(numbers) { it ->
            ItemNumber(number = it) { viewModel.setOneNumber(it) }
        }

    }

}
