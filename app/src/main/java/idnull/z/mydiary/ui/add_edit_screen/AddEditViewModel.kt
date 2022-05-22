package idnull.z.mydiary.ui.add_edit_screen


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.data.DiaryRepository
import idnull.z.mydiary.data.InternalStorageRepository
import idnull.z.mydiary.domain.DiaryUnit
import idnull.z.mydiary.domain.InternalStoragePhoto
import idnull.z.mydiary.utils.ImageOptimizer.getResizedBitmap
import idnull.z.mydiary.utils.convertDataFullInfo
import idnull.z.mydiary.utils.convertToData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val photoRepository: InternalStorageRepository,
    private val repository: DiaryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        showSnackBar(throwable.message ?: "Unexpected Error!")
    }
    private val data = Calendar.getInstance().timeInMillis
    private var oldDate = -1L
    private var currentId: Int? = null

    private val _diaryTitle = mutableStateOf(TextFieldState(hint = "Title"))
    val title: State<TextFieldState> = _diaryTitle

    private val _diaryContent = mutableStateOf(TextFieldState(hint = "Dear Diary"))
    val content: State<TextFieldState> = _diaryContent

    private val _screenState = mutableStateOf(AddEditScreenState())
    val screenState: State<AddEditScreenState> = _screenState

    private val _actionFlow = MutableSharedFlow<AddEditScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    private val internalImages = MutableStateFlow<List<InternalStoragePhoto>>(emptyList())

    init {
        viewModelScope.launch {
            savedStateHandle.get<Int>("id")?.let { id ->
                handleState(id)
            }
        }
        viewModelScope.launch {
            internalImages.collect { list ->
                val result = mutableListOf<InternalStoragePhoto>()
                for (i in list) {
                    val res = i.copy(bmp = getResizedBitmap(i.bmp))
                    result.add(res)
                }
                _screenState.value = _screenState.value.copy(images = result)
            }
        }
    }

    fun obtainEvent(event: AddEditScreenEvent) {
        when (event) {
            is AddEditScreenEvent.EnteredTitle -> {
                _diaryTitle.value = title.value.copy(
                    text = event.value
                )
            }
            is AddEditScreenEvent.ChangeTitleFocus -> {
                _diaryTitle.value = title.value.copy(
                    isHintVisible = !event.focusState.isFocused && title.value.text.isBlank()
                )
            }
            is AddEditScreenEvent.EnteredContent -> {
                _diaryContent.value = content.value.copy(text = event.value)
                _screenState.value =
                    screenState.value.copy(saveVisibility = _diaryContent.value.text.isNotEmpty())
            }
            is AddEditScreenEvent.ChangeContentFocus -> {
                _diaryContent.value = content.value.copy(
                    isHintVisible = !event.focusState.isFocused && content.value.text.isBlank()
                )
            }
            is AddEditScreenEvent.SaveDiary -> {
                viewModelScope.launch(errorHandler) {
                    val images = photoRepository.saveImages(images = screenState.value.images)
                    val utilsData = convertToData(data = data)
                    repository.insertDiary(
                        DiaryUnit(
                            title = title.value.text,
                            content = content.value.text,
                            date = if (currentId == null) data else oldDate,
                            id = currentId,
                            dateFromCheck = utilsData,
                            images = images
                        )
                    )
                    _actionFlow.emit(AddEditScreenAction.NavigateUp)
                }
            }
            is AddEditScreenEvent.DeleteDiary -> {
                viewModelScope.launch(errorHandler) {
                    currentId?.let { repository.deleteDiary(id = it) }
                    _actionFlow.emit(AddEditScreenAction.NavigateUp)
                }
            }
            is AddEditScreenEvent.AddBitmap -> {
                if (event.bitmap == null) {
                    showSnackBar("Error")
                    return
                }
                val images = internalImages.value.toMutableList()
                images.add(InternalStoragePhoto(event.bitmap))
                viewModelScope.launch { internalImages.emit(images) }
            }
            is AddEditScreenEvent.CancelDialog -> {
                showSnackBar("Selection Canceled")
            }
            is AddEditScreenEvent.ChangeSlider -> {
                _screenState.value = _screenState.value.copy(showSlider = event.isVisible)
            }

            is AddEditScreenEvent.Error -> {
                // TODO:  
            }
        }
    }

    private suspend fun handleState(id: Int) {
        if (id != -1) {
            _screenState.value =
                screenState.value.copy(saveVisibility = true, deleteVisibility = true)
            repository.getDairyById(id)?.also {
                currentId = it.id
                oldDate = it.date
                _screenState.value =
                    screenState.value.copy(date = convertDataFullInfo(it.date))

                _diaryTitle.value = title.value.copy(
                    text = it.title,
                    isHintVisible = false
                )
                _diaryContent.value = content.value.copy(
                    text = it.content,
                    isHintVisible = false
                )
                internalImages.emit(photoRepository.getAllPhotos(it.images))
            }
        } else {
            _screenState.value = screenState.value.copy(date = convertDataFullInfo(data))
        }
    }

    private fun showSnackBar(value: String) {
        viewModelScope.launch {
            _actionFlow.emit(
                AddEditScreenAction.ShowSnackBar(
                    message = value
                )
            )
        }
    }
}