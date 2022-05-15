package idnull.z.mydiary.ui.diary_list_screen

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.ui.diary_list_screen.components.DiaryListItem
import idnull.z.mydiary.ui.diary_list_screen.components.DiaryToolBar
import idnull.z.mydiary.ui.shared_component.BottomBar
import idnull.z.mydiary.ui.shared_component.FabButton
import idnull.z.mydiary.ui.theme.PerfectDark
import idnull.z.mydiary.utils.Screen
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalComposeUiApi
@SuppressLint("RememberReturnType")
@Composable
fun DiaryListScreen(
    navController: NavController,
    viewModel: DiaryListViewModel = hiltViewModel()
) {
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        floatingActionButton = { FabButton(navController) },
        backgroundColor = PerfectDark,
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            DiaryToolBar(viewModel = viewModel)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(viewModel.listDiary) {
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

    BackHandler {
        activity?.finish()
    }
}