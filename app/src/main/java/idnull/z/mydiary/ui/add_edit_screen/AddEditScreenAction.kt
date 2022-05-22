package idnull.z.mydiary.ui.add_edit_screen

sealed class AddEditScreenAction {
    data class ShowSnackBar(val message: String): AddEditScreenAction()
    object NavigateUp: AddEditScreenAction()
}
