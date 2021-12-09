package idnull.z.mydiary.ui.add_edit_screen

import androidx.compose.ui.focus.FocusState

sealed class AddEditScreenEvent{
    data class EnteredTitle(val value: String): AddEditScreenEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditScreenEvent()
    data class EnteredContent(val value: String): AddEditScreenEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditScreenEvent()

    object SaveDiary: AddEditScreenEvent()
    object DeleteDiary: AddEditScreenEvent()
}

