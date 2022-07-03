package idnull.z.mydiary.ui.shared_component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import idnull.z.mydiary.data.DataStoreRepository
import idnull.z.mydiary.utils.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComposeViewModel @Inject constructor(
    private val repository: DataStoreRepository,
) : ViewModel() {
    fun navigateToAddEditScreen(navController: NavController) {
        viewModelScope.launch {
            val todayDiaryId = repository.readDiaryId()
            navController.navigate(Screen.AddEditDiaryScreen.route + "?id=${todayDiaryId.toLong()}")
        }
    }
}