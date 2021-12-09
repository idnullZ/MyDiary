package idnull.z.mydiary.data


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import idnull.z.mydiary.dataStore

import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject


class DataStoreRepository @Inject constructor(
   @ApplicationContext val context: Context
) {
    private val isFirsOpenKey = booleanPreferencesKey("isFirsOpen")
    private val codeKey = stringPreferencesKey("CodeOpen")
    suspend fun saveFirstOpen(isFirstOpen: Boolean) {
        context.dataStore.edit {
            it[isFirsOpenKey] = isFirstOpen
        }
    }
     suspend fun readFirstOpen(): Boolean {
        return context.dataStore.data.firstOrNull()?.get(isFirsOpenKey) ?: true

    }

   suspend fun savePasscode(code:String){
       context.dataStore.edit {
           it[codeKey]= code
       }

    }

   suspend fun readPasscode():String{
       return context.dataStore.data.firstOrNull()?.get(codeKey) ?:""
   }

}