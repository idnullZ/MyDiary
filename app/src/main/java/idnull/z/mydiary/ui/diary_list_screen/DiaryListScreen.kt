package idnull.z.mydiary.ui.diary_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.domain.themes.AppTheme
import idnull.z.mydiary.ui.diary_list_screen.components.DiaryListItem
import idnull.z.mydiary.ui.diary_list_screen.components.DiaryToolBar
import idnull.z.mydiary.utils.Screen
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@SuppressLint("RememberReturnType")
@Composable
fun DiaryListScreen(
    navController: NavController,
    viewModel: DiaryListViewModel = hiltViewModel()
    ,
    onItemSelected: (AppTheme) -> Unit
) {



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addEditScreen")
                },
                backgroundColor = Color(0xff639dff),
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff181c20))
        ) {

            DiaryToolBar(
                viewModel = viewModel
                ,
                onItemSelected = onItemSelected

            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(viewModel.listDiary.value) {
                    DiaryListItem(diary = it, modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.AddEditDiaryScreen.route +
                                    "?id=${it.id}"
                        )

                    })
                }
            }
        }
    }



}



