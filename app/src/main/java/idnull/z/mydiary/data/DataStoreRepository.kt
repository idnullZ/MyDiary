package idnull.z.mydiary.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import idnull.z.mydiary.dataStore
import idnull.z.mydiary.utils.Screen
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val firstScreen = stringPreferencesKey("firstScreen")
    private val codeKey = stringPreferencesKey("CodeOpen")
    private val diaryId = intPreferencesKey("diaryId")

    suspend fun savePasscode(code: String) {
        context.dataStore.edit { it[codeKey] = code }
        saveFirstScreen(Screen.CodeScreen.route)
    }

    suspend fun readPasscode() = context.dataStore.data.firstOrNull()?.get(codeKey) ?: ""

    suspend fun saveDiaryId(id: Int) = context.dataStore.edit { it[diaryId] = id }

    suspend fun readDiaryId() = context.dataStore.data.firstOrNull()?.get(diaryId) ?: -1

    private suspend fun saveFirstScreen(screen: String) {
        context.dataStore.edit {
            it[firstScreen] = screen
        }
    }

    suspend fun readFirstScreen() =
        context.dataStore.data.firstOrNull()?.get(firstScreen) ?: Screen.DairyListScreen.route

}