package idnull.z.mydiary.ui.add_edit_screen

import android.Manifest
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import idnull.z.mydiary.ui.add_edit_screen.components.*
import idnull.z.mydiary.ui.shared_component.PermissionApp
import idnull.z.mydiary.utils.getCameraCaptureIntentWithName
import idnull.z.mydiary.utils.getGalleryCaptureIntent
import idnull.z.mydiary.utils.openSettings
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.InputStream

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel(),
) {
    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        viewModel.obtainEvent(AddEditScreenEvent.Error(throwable.message ?: "Unexpected Error!"))
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val titleState = viewModel.title.value
    val contentState = viewModel.content.value
    val screenState = viewModel.screenState.value
    val scaffoldState = rememberScaffoldState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    var cameraFileName by remember { mutableStateOf("") }
    var requestPermission by remember { mutableStateOf(false) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data
                scope.launch(errorHandler) {
                    val imageStream: InputStream? =
                        imageUri?.let { context.contentResolver.openInputStream(it) }
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    viewModel.obtainEvent(AddEditScreenEvent.AddBitmap(selectedImage))
                }
            }
        }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.obtainEvent(AddEditScreenEvent.SaveCameraImage(cameraFileName))
            }
        }
    BottomSheet(bottomSheetScaffoldState, answer = { choiceDialog ->
        when (choiceDialog) {
            is ChoiceDialog.Camera -> {
                val (intent, fileName) = getCameraCaptureIntentWithName()
                cameraFileName = fileName
                cameraLauncher.launch(intent)
            }
            is ChoiceDialog.Gallery -> galleryLauncher.launch(getGalleryCaptureIntent())
            is ChoiceDialog.Cancel -> viewModel.obtainEvent(AddEditScreenEvent.CancelDialog)
        }
        scope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
    }) {
        Scaffold(scaffoldState = scaffoldState) {
            if (requestPermission) {
                PermissionApp(
                    permissions = listOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
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
                        ImageAdapter(images = screenState.images)
                        CurrentDate(date = screenState.date) { requestPermission = true }
                        TextFields(titleState, viewModel, contentState)
                    }
                    LaunchedEffect(key1 = Unit) {
                        viewModel.actionFlow.collectLatest {
                            when (it) {
                                is AddEditScreenAction.ShowSnackBar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = it.message
                                    )
                                }
                                is AddEditScreenAction.SaveDiary -> navController.navigateUp()
                                is AddEditScreenAction.DeleteDiary -> navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}