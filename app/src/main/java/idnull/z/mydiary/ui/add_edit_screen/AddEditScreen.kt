package idnull.z.mydiary.ui.add_edit_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.ui.add_edit_screen.components.ControlTextField
import idnull.z.mydiary.ui.add_edit_screen.components.ToolBar
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditScreenViewModel = hiltViewModel(),
    ) {
    val titleState = viewModel.title.value
    val contentState = viewModel.content.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff14181c))
        ) {

            ToolBar(navController = navController, viewModel)


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .padding(16.dp)

                ) {
                    /**  Title TextField */
                    ControlTextField(
                        singleLine = true,
                        maxLines = 1,
                        text = titleState.text,
                        hint = titleState.hint,
                        onValueChange = {
                            viewModel.obtainEvent(AddEditScreenEvent.EnteredTitle(it))
                        },
                        onFocusChange = {
                            viewModel.obtainEvent(AddEditScreenEvent.ChangeTitleFocus(it))
                        },
                        isHintVisible = titleState.isHintVisible,
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Normal,
                            fontSize = 28.sp,
                            letterSpacing = 0.sp
                        ),
                        modifier = Modifier.fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    /**  Content TextField */
                    ControlTextField(

                        text = contentState.text,
                        hint = contentState.hint,
                        onValueChange = {
                            viewModel.obtainEvent(AddEditScreenEvent.EnteredContent(it))
                        },
                        onFocusChange = {
                            viewModel.obtainEvent(AddEditScreenEvent.ChangeContentFocus(it))
                        },
                        isHintVisible = contentState.isHintVisible,

                        textStyle = TextStyle(

                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            letterSpacing = 0.5.sp
                        ),

                        modifier = Modifier.fillMaxWidth()
                    )

                }




            LaunchedEffect(key1 = true) {

                viewModel.actionFlow.collectLatest {
                    when (it) {
                        is AddEditScreenAction.ShowSnackBar -> {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = it.message
                            )
                        }
                        is AddEditScreenAction.SaveDiary -> {
                            navController.navigateUp()
                        }

                        is AddEditScreenAction.DeleteDiary -> {

                            navController.navigateUp()
                        }
                    }

                }
            }


        }

    }


}