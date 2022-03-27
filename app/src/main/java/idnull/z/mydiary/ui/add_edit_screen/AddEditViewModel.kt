package idnull.z.mydiary.ui.add_edit_screen


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.data.DiaryRepository
import idnull.z.mydiary.data.TemporaryStorage
import idnull.z.mydiary.domain.DiaryUnit
import idnull.z.mydiary.utils.convertDataFullInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: DiaryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val data = Calendar.getInstance().timeInMillis
    private var oldDate = -1L
    private var currentId: Int? = null

    var date = mutableStateOf("")
        private set

    var saveVisibility = mutableStateOf(false)
        private set

    var deleteVisibility = mutableStateOf(false)
        private set

    private val _diaryTitle = mutableStateOf(
        TextFieldState(
            hint = "Title"
        )
    )
    val title: State<TextFieldState> = _diaryTitle

    private val _diaryContent = mutableStateOf(
        TextFieldState(
            hint = "Dear Diary"
        )
    )
    val content: State<TextFieldState> = _diaryContent

    private val _actionFlow = MutableSharedFlow<AddEditScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()


    init {
        viewModelScope.launch {
            savedStateHandle.get<Int>("id")?.let { id ->
                handleState(id)
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
                    isHintVisible = !event.focusState.isFocused &&
                            title.value.text.isBlank()
                )
            }
            is AddEditScreenEvent.EnteredContent -> {
                _diaryContent.value = content.value.copy(
                    text = event.value
                )
                saveVisibility.value = _diaryContent.value.text.isNotEmpty()


            }

            is AddEditScreenEvent.ChangeContentFocus -> {
                _diaryContent.value = content.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            content.value.text.isBlank()
                )
            }

            is AddEditScreenEvent.SaveDiary -> {
                viewModelScope.launch {
                    val utilsData = convertToData(data = data)
                    try {
                        repository.insertDiary(
                            DiaryUnit(
                                title = title.value.text,
                                content = content.value.text,
                                date = if (currentId == null) data else oldDate,
                                id = currentId,
                                dateFromCheck = utilsData
                            )
                        )
                        _actionFlow.emit(AddEditScreenAction.SaveDiary)
                    } catch (e: Exception) {

                        _actionFlow.emit(

                            AddEditScreenAction.ShowSnackBar(
                                message = e.message ?: "Unexpected Error!"
                            )
                        )
                    }
                }

            }

            is AddEditScreenEvent.DeleteDiary -> {
                viewModelScope.launch {
                    try {
                        currentId?.let {
                            repository.deleteDiary(
                                id = it
                            )
                            if (TemporaryStorage.id == it) {
                                TemporaryStorage.id = -1
                            }
                        }

                        _actionFlow.emit(AddEditScreenAction.SaveDiary)
                    } catch (e: Exception) {
                        _actionFlow.emit(
                            AddEditScreenAction.ShowSnackBar(
                                message = e.message ?: "Unexpected Error!"
                            )
                        )
                    }
                }
            }
        }
    }

    private suspend fun handleState(id: Int) {
        if (id != -1) {
            saveVisibility.value = true
            deleteVisibility.value = true
            repository.getDairyById(id)?.also {
                currentId = it.id
                oldDate = it.date
                date.value = convertDataFullInfo(it.date)
                _diaryTitle.value = title.value.copy(
                    text = it.title,
                    isHintVisible = false
                )
                _diaryContent.value = content.value.copy(
                    text = it.content,
                    isHintVisible = false
                )
            }
        } else {
            date.value = convertDataFullInfo(data)
        }
    }


    private fun convertToData(data: Long): String {
        val format = "d.MMM.yyyy"
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        val utilsData = dateFormat.format(data).toString()
        return utilsData
    }

}