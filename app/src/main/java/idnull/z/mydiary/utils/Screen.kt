package idnull.z.mydiary.utils

sealed class Screen(val route:String){
    object CodeScreen:Screen("codeScreen")
    object DairyListScreen:Screen("diaryListScreen")
    object AddEditDiaryScreen:Screen("addEditScreen")

}
