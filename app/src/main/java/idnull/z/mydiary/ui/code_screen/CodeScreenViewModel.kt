package idnull.z.mydiary.ui.code_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.data.DataStoreRepository
import idnull.z.mydiary.utils.loger
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CodeScreenViewModel @Inject constructor(
    private val repository: DataStoreRepository,
) : ViewModel() {
    private var _isFirsOpen: Boolean = false
    val isFirstOpen = mutableStateOf(false)


    private val _actionFlow = MutableSharedFlow<CodeScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()


    val countItemPassword = mutableStateOf(0)
    val passwordCorrect = mutableStateOf(false)


    private val _numbers = mutableStateListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", " ", "0", " ")
    val numbers = _numbers

    private var nawCode = ""
//    init {
//        init()
//    }

    fun setOneNumber(number: String) {
        nawCode += number
        countItemPassword.value++
        checkingPasscode()
    }

    private fun checkingPasscode() {
        if (countItemPassword.value == 4) {
            if (_isFirsOpen) {
                saveCodeToRepository()
            } else {
                if (checkingEqualityPassword()) {
                    passwordCorrect.value = true
                }else{
                    viewModelScope.launch {
                        _actionFlow.emit(
                            CodeScreenAction.ShowSnackBar(
                                message = "Code incorrect! Please try again"
                            )
                        )
                    }
                }
            }
            countItemPassword.value = 0
            nawCode = ""
        }
    }


     fun init() {


        viewModelScope.launch {
            _isFirsOpen = repository.readFirstOpen()
            setIsFiresOpen()
        }
    }

    private fun saveCodeToRepository() {
        val value = nawCode
        viewModelScope.launch {
            repository.savePasscode(nawCode)
            _actionFlow.emit(CodeScreenAction.ShowSnackBar(
                message = "Your code $value"
            ))
            nawCode = ""
            _isFirsOpen = false
            repository.saveFirstOpen(false)
        }
    }


    private fun checkingEqualityPassword():Boolean{
         var trueCode = ""
        viewModelScope.launch {
            trueCode = repository.readPasscode()
        }
        return  trueCode == nawCode
    }

   private suspend fun setIsFiresOpen(){
        isFirstOpen.value = repository.readFirstOpen()
       _actionFlow.emit(CodeScreenAction.SetIsFirstOpenValue)

    }


}