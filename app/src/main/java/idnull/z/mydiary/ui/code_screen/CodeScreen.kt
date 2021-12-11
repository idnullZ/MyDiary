package idnull.z.mydiary.ui.code_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.ui.code_screen.components.GridNumbers
import idnull.z.mydiary.ui.code_screen.components.Points
import idnull.z.mydiary.ui.code_screen.components.ShowAlertDialog
import idnull.z.mydiary.utils.Screen
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalFoundationApi
@Composable
fun CodeScreen(
    navController: NavController,
    viewModel: CodeScreenViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    var stateIsFirstOpen by remember { mutableStateOf(false) }
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.surface),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Points(viewModel = viewModel)
            GridNumbers(numbers = viewModel.numbers, viewModel = viewModel)
        }
    }
    if (viewModel.passwordCorrect.value) {
        viewModel.passwordCorrect.value = false

        navController.navigate(Screen.DairyListScreen.route){
            popUpTo(Screen.CodeScreen.route){
                inclusive = true
            }
        }

    }
    ShowAlertDialog(
        textMain = "please set new code",
        textTitle = "Warning",
        showState = stateIsFirstOpen
    ) {
        stateIsFirstOpen = false
    }
    LaunchedEffect(key1 = true) {
        viewModel.actionFlow.collectLatest {
            when (it) {
                is CodeScreenAction.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.message
                    )
                }
                is CodeScreenAction.SetIsFirstOpenValue -> {
                    stateIsFirstOpen = viewModel.isFirstOpen.value
                }
            }
        }
    }


    LaunchedEffect(key1 = Unit){

        viewModel.init()
    }
}



