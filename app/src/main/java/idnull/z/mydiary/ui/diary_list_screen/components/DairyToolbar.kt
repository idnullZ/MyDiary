package idnull.z.mydiary.ui.diary_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.ui.add_edit_screen.components.ControlTextField
import idnull.z.mydiary.ui.diary_list_screen.DairyListScreenEvent
import idnull.z.mydiary.ui.diary_list_screen.DiaryListViewModel
import idnull.z.mydiary.ui.shared_component.TopText
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@Composable
fun DiaryToolBar(viewModel: DiaryListViewModel) {
    val searchBarState = viewModel.searchBar.value
    var searchBarVisibility by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (searchBarVisibility) {
            Row(
                modifier = Modifier
                    .height(56.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = "back",
                        modifier = Modifier
                            .size(36.dp)
                            .clickable {
                                searchBarVisibility = false
                                viewModel.recoverSearch()

                            },
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                ControlTextField(
                    singleLine = true,
                    maxLines = 1,
                    text = searchBarState.text,
                    hint = searchBarState.hint,
                    onValueChange = {
                        viewModel.obtainEvent(DairyListScreenEvent.EnteredSearchBar(it))
                    },
                    onFocusChange = {
                        viewModel.obtainEvent(DairyListScreenEvent.ChangeSearchBarFocus(it))
                    },
                    isHintVisible = searchBarState.isHintVisible,
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        letterSpacing = 0.5.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            TopText(text = "My Diary")
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search",
                    modifier = Modifier
                        .clickable {
                            searchBarVisibility = true
                        }
                        .padding(horizontal = 8.dp),
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}
