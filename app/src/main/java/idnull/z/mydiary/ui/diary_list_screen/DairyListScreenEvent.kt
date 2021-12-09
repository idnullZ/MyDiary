package idnull.z.mydiary.ui.diary_list_screen

import androidx.compose.ui.focus.FocusState

sealed class DairyListScreenEvent{

    data class EnteredSearchBar(val value: String): DairyListScreenEvent()
    data class ChangeSearchBarFocus(val focusState: FocusState): DairyListScreenEvent()




}
