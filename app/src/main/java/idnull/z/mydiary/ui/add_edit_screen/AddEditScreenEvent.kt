package idnull.z.mydiary.ui.add_edit_screen

import android.graphics.Bitmap
import androidx.compose.ui.focus.FocusState
import idnull.z.mydiary.domain.InternalStoragePhoto
import idnull.z.mydiary.domain.SmileIcons

sealed class AddEditScreenEvent {
    data class EnteredTitle(val value: String) : AddEditScreenEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditScreenEvent()
    data class EnteredContent(val value: String) : AddEditScreenEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditScreenEvent()
    data class AddBitmap(val bitmap: Bitmap?) : AddEditScreenEvent()
    data class Error(val value: String?) : AddEditScreenEvent()
    data class SliderVisibility(val isVisible: Boolean) : AddEditScreenEvent()
    data class DeleteImage(val photo: InternalStoragePhoto) : AddEditScreenEvent()
    data class SmileSavaClick(val smiles: List<SmileIcons>) : AddEditScreenEvent()

    object SaveDiary : AddEditScreenEvent()
    object BackPress : AddEditScreenEvent()
    object DeleteDiary : AddEditScreenEvent()
}

