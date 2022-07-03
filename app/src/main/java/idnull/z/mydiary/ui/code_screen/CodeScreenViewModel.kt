package idnull.z.mydiary.ui.code_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.data.DataStoreRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CodeScreenViewModel @Inject constructor(
    private val repository: DataStoreRepository,
) : ViewModel() {
    private var _isFirsOpen: Boolean = true
    private var nawCode = ""

    private val list = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", " ", "0", " ")

    private val _actionFlow = MutableSharedFlow<CodeScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    var countItemPassword by mutableStateOf(0)
        private set
    var passwordCorrect by mutableStateOf(false)
        private set

    val numbers by mutableStateOf(list)

    fun setOneNumber(number: String) {
        nawCode += number
        countItemPassword++
        checkingPasscode()
    }

    fun passwordCorrected() {
        passwordCorrect = false
    }

    private fun checkingPasscode() {
        if (countItemPassword == 4) {
            if (_isFirsOpen) {
                saveCodeToRepository()
            } else {
                if (checkingEqualityPassword()) {
                    passwordCorrect = true
                } else {
                    viewModelScope.launch {
                        _actionFlow.emit(
                            CodeScreenAction.ShowSnackBar(
                                message = "Code incorrect! Please try again"
                            )
                        )
                    }
                }
            }
            countItemPassword = 0
            nawCode = ""
        }
    }

    private fun saveCodeToRepository() {
        val value = nawCode
        viewModelScope.launch {
            repository.savePasscode(nawCode)
            _actionFlow.emit(
                CodeScreenAction.ShowSnackBar(
                    message = "Your code $value"
                )
            )
            nawCode = ""
            _isFirsOpen = false
        }
    }

    private fun checkingEqualityPassword(): Boolean {
        var trueCode = ""
        viewModelScope.launch {
            trueCode = repository.readPasscode()
        }
        return trueCode == nawCode
    }

}