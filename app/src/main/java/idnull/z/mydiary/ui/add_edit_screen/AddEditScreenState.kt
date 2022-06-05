package idnull.z.mydiary.ui.add_edit_screen

import idnull.z.mydiary.domain.InternalStoragePhoto
import idnull.z.mydiary.domain.SmileIcons

data class AddEditScreenState(
    val date: String = "",
    val saveVisibility: Boolean = false,
    val deleteVisibility: Boolean = false,
    val showSlider: Boolean = false,
    val images: List<InternalStoragePhoto> = emptyList(),
    val smile: List<SmileIcons> = emptyList(),


){
    val smilesSelected: List<SmileIcons>
    get() = smile.filter { it.isSelect }
}