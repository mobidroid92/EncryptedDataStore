package com.bdour.encrypteddatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.bdour.encrypteddatastore.encryption.EncryptionHelper
import com.bdour.encrypteddatastore.encryption.EncryptionHelperImpl
import com.bdour.encrypteddatastore.utils.toStringOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class EncryptedDataStore (
    private val context: Context,
    private val encryptionHelper: EncryptionHelper = EncryptionHelperImpl(),
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
        name = DATA_STORE_NAME,
        scope = scope
    )

    //region get data
    fun getInt(key: Preferences.Key<String>, defaultValue: Int): Flow<Int> {
        return getString(key).map { it?.toInt() ?: defaultValue }
    }

    fun getLong(key: Preferences.Key<String>, defaultValue: Long): Flow<Long> {
        return getString(key).map { it?.toLong() ?: defaultValue }
    }

    fun getFloat(key: Preferences.Key<String>, defaultValue: Float): Flow<Float> {
        return getString(key).map { it?.toFloat() ?: defaultValue }
    }

    fun getDouble(key: Preferences.Key<String>, defaultValue: Double): Flow<Double> {
        return getString(key).map { it?.toDouble() ?: defaultValue }
    }

    fun getBoolean(key: Preferences.Key<String>, defaultValue: Boolean): Flow<Boolean> {
        return getString(key).map { it?.toBoolean() ?: defaultValue }
    }

    fun getString(key: Preferences.Key<String>): Flow<String?> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val encryptedValue = preferences[key]
            if(encryptedValue.isNullOrEmpty()) {
                return@map null
            }
            encryptionHelper.decryptData(encryptedValue)
        }
    }
    //endregion

    //region put data
    suspend fun putInt(key: Preferences.Key<String>, newValue: Int?) {
        putString(key, newValue?.toStringOrNull())
    }

    suspend fun putLong(key: Preferences.Key<String>, newValue: Long?) {
        putString(key, newValue?.toStringOrNull())
    }

    suspend fun putFloat(key: Preferences.Key<String>, newValue: Float?) {
        putString(key, newValue?.toStringOrNull())
    }

    suspend fun putDouble(key: Preferences.Key<String>, newValue: Double?) {
        putString(key, newValue?.toStringOrNull())
    }

    suspend fun putBoolean(key: Preferences.Key<String>, newValue: Boolean?) {
        putString(key, newValue?.toStringOrNull())
    }

    suspend fun putString(key: Preferences.Key<String>, newValue: String?) {
        newValue?.let { newValueNotNull ->
            val encryptedValue = encryptionHelper.encryptData(newValueNotNull)
            context.dataStore.edit { mutablePreferences ->
                mutablePreferences[key] = encryptedValue
            }
        }
    }
    //endregion

    suspend fun clear() {
        context.dataStore.edit { mutablePreferences -> mutablePreferences.clear() }
    }

    companion object {
        private const val DATA_STORE_NAME = "EncryptedDataStorePreferences"
    }

}