package idnull.z.mydiary.ui.calendar_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import idnull.z.mydiary.ui.code_screen.components.ItemNumber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(numbers: List<String>) {


    LazyVerticalGrid(cells = GridCells.Fixed(count =7)){

        items(numbers) {
            ItemDay(text = it)
        }

    }
}