package idnull.z.mydiary.ui.code_screen

sealed class CodeScreenAction{
    data class ShowSnackBar(val message:String):CodeScreenAction()
}
