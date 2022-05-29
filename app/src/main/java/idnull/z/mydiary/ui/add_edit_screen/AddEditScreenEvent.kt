package idnull.z.mydiary.ui.add_edit_screen

import android.graphics.Bitmap
import androidx.compose.ui.focus.FocusState
import idnull.z.mydiary.domain.InternalStoragePhoto

sealed class AddEditScreenEvent {
    data class EnteredTitle(val value: String) : AddEditScreenEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditScreenEvent()
    data class EnteredContent(val value: String) : AddEditScreenEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditScreenEvent()
    data class AddBitmap(val bitmap: Bitmap?) : AddEditScreenEvent()
    data class Error(val value: String?) : AddEditScreenEvent()
    data class ChangeSlider(val isVisible: Boolean) : AddEditScreenEvent()
    data class DeleteImage(val photo: InternalStoragePhoto) : AddEditScreenEvent()

    object SaveDiary : AddEditScreenEvent()
    object CancelDialog : AddEditScreenEvent()
    object DeleteDiary : AddEditScreenEvent()
}

