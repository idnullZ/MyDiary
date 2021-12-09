package idnull.z.mydiary.ui.diary_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.data.DiaryRepository
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.DiaryUnit
import idnull.z.mydiary.domain.use_case.GetListDiaryUseCase
import idnull.z.mydiary.ui.add_edit_screen.TextFieldState
import idnull.z.mydiary.utils.loger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    private val repository: DiaryRepository,
   private val getListDiaryUseCase: GetListDiaryUseCase
) :
    ViewModel() {


    var listDiary = mutableStateOf<List<Diary>>(listOf())
    private set

    private var cacheList = listOf<Diary>()

    private var isSearchStarted = true

    private var isSearch = false


    private val _searchBar = mutableStateOf(
        TextFieldState(
            hint = "search..."
        )
    )
    val searchBar: State<TextFieldState> = _searchBar


    @FlowPreview
    fun obtainEvent(event: DairyListScreenEvent) {

        when (event) {

            is DairyListScreenEvent.EnteredSearchBar -> {
                _searchBar.value = searchBar.value.copy(
                    text = event.value
                )
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

    private fun getDiary() {
        viewModelScope.launch {

            getListDiaryUseCase.invoke().collect{


                listDiary.value = it

            }


        }
    }

    private fun search(query: String) {

        val searchList = if (isSearchStarted) {
            listDiary.value
        } else {
            cacheList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty() || query == "") {
                listDiary.value = cacheList
                isSearch = false
                isSearchStarted = true
                return@launch
            }
            val result = searchList.filter {
                it.content.contains(query.trim(), ignoreCase = true) ||
                        it.title.contains(query.trim(), ignoreCase = true)
            }

            if (isSearchStarted) {
                cacheList = listDiary.value
                isSearchStarted = false
            }

            listDiary.value = result

        }

    }

    fun testFun() {
        _searchBar.value = searchBar.value.copy(
            text = ""
        )
    }


}