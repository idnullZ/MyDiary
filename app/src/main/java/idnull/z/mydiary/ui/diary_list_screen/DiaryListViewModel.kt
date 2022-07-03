package idnull.z.mydiary.ui.diary_list_screen

import android.icu.util.Calendar
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.data.DataStoreRepository
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.use_case.GetListDiaryUseCase
import idnull.z.mydiary.ui.add_edit_screen.TextFieldState
import idnull.z.mydiary.utils.convertData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val repository: DataStoreRepository,
    private val getListDiaryUseCase: GetListDiaryUseCase
) : ViewModel() {
    private var cacheList = listOf<Diary>()
    private val calendar = Calendar.getInstance()

    var listDiary by mutableStateOf<List<Diary>>(listOf())
        private set

    private val _searchBar = mutableStateOf(TextFieldState(hint = "search..."))
    val searchBar: State<TextFieldState> = _searchBar

    @FlowPreview
    fun obtainEvent(event: DairyListScreenEvent) {
        when (event) {
            is DairyListScreenEvent.EnteredSearchBar -> {
                _searchBar.value = searchBar.value.copy(text = event.value)
                search(_searchBar.value.text)
            }
            is DairyListScreenEvent.ChangeSearchBarFocus -> {
                _searchBar.value = searchBar.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            searchBar.value.text.isBlank()
                )
            }
        }
    }

    init {
        getDiary()
    }

    private fun savaInitialData(data: List<Diary>) {

        viewModelScope.launch {
            if (data.isEmpty()) {
                repository.saveDiaryId(-1)
                return@launch
            }
            val curData = convertData(calendar.timeInMillis)
            if (data.first().date == curData) {
                data.first().id?.let { repository.saveDiaryId(it) }
            } else {
                repository.saveDiaryId(-1)
            }
        }
    }

    private fun getDiary() {
        viewModelScope.launch {
            getListDiaryUseCase.invoke().collect {
                listDiary = it
                cacheList = it
                savaInitialData(it)
            }
        }
    }

    private fun search(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val result = if (query.isEmpty()) {
                cacheList
            } else {
                cacheList.filter {
                    it.content.contains(query.trim(), ignoreCase = true) ||
                            it.title.contains(query.trim(), ignoreCase = true)
                }
            }
            listDiary = result
        }
    }

    fun recoverSearch() {
        _searchBar.value = searchBar.value.copy(text = EMPTY)
        search(EMPTY)
    }

    companion object {
        private const val EMPTY = ""
    }
}