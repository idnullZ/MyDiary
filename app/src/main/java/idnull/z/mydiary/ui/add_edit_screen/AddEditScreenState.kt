package idnull.z.mydiary.ui.add_edit_screen

import idnull.z.mydiary.domain.InternalStoragePhoto

data class AddEditScreenState(
    val date: String = "",
    val saveVisibility: Boolean = false,
    val deleteVisibility: Boolean = false,
    val images: MutableList<InternalStoragePhoto> = emptyList<InternalStoragePhoto>().toMutableList()
)