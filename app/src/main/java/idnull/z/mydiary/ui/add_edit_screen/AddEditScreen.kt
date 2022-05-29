package idnull.z.mydiary.ui.add_edit_screen

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import idnull.z.mydiary.ui.add_edit_screen.components.*
import idnull.z.mydiary.ui.shared_component.PermissionApp
import idnull.z.mydiary.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@ExperimentalMaterialApi
@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val titleState = viewModel.title.value
    val contentState = viewModel.content.value
    val screenState = viewModel.screenState.value
    val scaffoldState = rememberScaffoldState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    var requestPermission by rememberSaveable { mutableStateOf(false) }
    var showImageAdapter by rememberSaveable { mutableStateOf(true) }
    var cameraName by rememberSaveable { mutableStateOf("") }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            loger(result)
            if (result.resultCode == Activity.RESULT_OK) {
                scope.launch {
                    viewModel.obtainEvent(AddEditScreenEvent.AddBitmap(getImageWithName(cameraName)))
                }
            }
        }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data
                scope.launch(Dispatchers.IO) {
                    try {
                        val stream = imageUri?.let { context.contentResolver.openInputStream(it) }
                        val selectedImage = BitmapFactory.decodeStream(stream)
                        viewModel.obtainEvent(AddEditScreenEvent.AddBitmap(selectedImage))
                    } catch (error: Exception) {
                        viewModel.obtainEvent(AddEditScreenEvent.Error(error.message))
                    }

                }
            }
        }
    BottomSheet(bottomSheetScaffoldState, answer = { choiceDialog ->
        when (choiceDialog) {
            is ChoiceDialog.Camera -> {
                val (intent, fileName) = getCameraCaptureWithNameIntent()
                cameraName = fileName
                cameraLauncher.launch(intent)
            }
            is ChoiceDialog.Gallery -> galleryLauncher.launch(getGalleryCaptureIntent())
            is ChoiceDialog.Cancel -> viewModel.obtainEvent(AddEditScreenEvent.CancelDialog)
        }
        scope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
    }) {
        if (screenState.showSlider) {
            ImageSlider(
                images = viewModel.bigImages.value,
                closeClick = { viewModel.obtainEvent(AddEditScreenEvent.ChangeSlider(false)) },
                deleteClick = { viewModel.obtainEvent(AddEditScreenEvent.DeleteImage(it)) }
            )
        }
        Scaffold(scaffoldState = scaffoldState) {
            if (requestPermission) {
                LaunchedEffect(key1 = Unit) { requestPermission = checkPermissionImage(context) }
                PermissionApp(
                    permissions = mediaPermissions,
                    openSettings = { openSettings(context) },
                    closeMessage = { requestPermission = false },
                    content = {
                        requestPermission = false
                        scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                    }
                )
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    ToolBar(
                        navController = navController,
                        viewModel = viewModel,
                        deleteVisibility = screenState.deleteVisibility,
                        saveVisibility = screenState.saveVisibility
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.surface)
                    ) {
                        if (showImageAdapter) {
                            ImageAdapter(images = screenState.images) {
                                viewModel.obtainEvent(AddEditScreenEvent.ChangeSlider(true))
                            }
                        }
                        CurrentDate(
                            screenState.images,
                            date = screenState.date,
                            addClick = { requestPermission = true }
                        ) { showImageAdapter = !showImageAdapter }
                        TextFields(titleState, viewModel, contentState)
                    }
                    LaunchedEffect(key1 = Unit) {
                        viewModel.actionFlow.collectLatest {
                            when (it) {
                                is AddEditScreenAction.ShowSnackBar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(message = it.message)
                                }
                                is AddEditScreenAction.NavigateUp -> navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}