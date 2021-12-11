package idnull.z.mydiary.ui.diary_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.domain.themes.AppTheme
import idnull.z.mydiary.ui.add_edit_screen.components.ControlTextField
import idnull.z.mydiary.ui.diary_list_screen.DairyListScreenEvent
import idnull.z.mydiary.ui.diary_list_screen.DiaryListViewModel
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@Composable
fun DiaryToolBar(
    viewModel: DiaryListViewModel,
    onItemSelected: (AppTheme) -> Unit
) {
    val searchBarState = viewModel.searchBar.value

    var searchBarVisibility by remember { mutableStateOf(false) }

    var menuExpanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
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
                                viewModel.testFun()

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
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


        } else {
            Text(
                text = "My Diary",
                fontSize = 24.sp,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(start = 16.dp)

            )

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


                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "MoreVert",
                    modifier = Modifier
                        .clickable {
                            menuExpanded = true
                        }
                        .padding(horizontal = 8.dp),
                    tint = MaterialTheme.colors.onBackground
                )

                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                ) {
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = {
                            menuExpanded = false
                        },
                        modifier = Modifier
                            .width(200.dp)
                            .wrapContentSize(Alignment.TopStart)
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                onItemSelected(AppTheme.fromOrdinal(AppTheme.MODE_AUTO.ordinal))
                                menuExpanded = false
                            }
                        ) {
                            Text(text = "Auto",color = MaterialTheme.colors.onBackground)
                        }
                        DropdownMenuItem(
                            onClick = {
                                onItemSelected(AppTheme.fromOrdinal(AppTheme.MODE_DAY.ordinal))
                                menuExpanded = false
                            }
                        ) {
                            Text(text = "Light Theme" ,color = MaterialTheme.colors.onBackground)
                        }
                        DropdownMenuItem(
                            onClick = {
                                onItemSelected(AppTheme.fromOrdinal(AppTheme.MODE_NIGHT.ordinal))
                                menuExpanded = false
                            }
                        ) {
                            Text(text = "Dark Theme",color = MaterialTheme.colors.onBackground)
                        }
                    }
                }
            }


        }


    }

}
