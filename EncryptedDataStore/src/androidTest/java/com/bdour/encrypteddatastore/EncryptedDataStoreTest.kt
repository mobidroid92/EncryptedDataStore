package com.bdour.encrypteddatastore

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
class EncryptedDataStoreTest {

    private lateinit var testScope: TestScope
    private lateinit var encryptedDataStore: EncryptedDataStore

    @Before
    fun setup() {

        testScope = TestScope()
        encryptedDataStore = EncryptedDataStore(
            context = ApplicationProvider.getApplicationContext(),
            scope = testScope
        )
    }

    @After
    fun teardown() {
        testScope.cancel()
    }

    @Test
    fun saveAndRestoreStringValueShouldBeTheSame() = testScope.runTest {
        val stringValue = "Hello"
        val stringValueKey = stringPreferencesKey("string_value_key")
        encryptedDataStore.putString(stringValueKey, stringValue)
        val storedValue = encryptedDataStore.getString(stringValueKey).first()
        assertEquals(stringValue, storedValue)
    }

    @Test
    fun saveAndRestoreIntValueShouldBeTheSame() = testScope.runTest {
        val intValue = 4
        val intValueKey = stringPreferencesKey("int_value_key")
        encryptedDataStore.putInt(intValueKey, intValue)
        val storedValue = encryptedDataStore.getInt(intValueKey, 0).first()
        assertEquals(intValue, storedValue)
    }

    @Test
    fun saveAndRestoreLongValueShouldBeTheSame() = testScope.runTest {
        val longValue = 4L
        val longValueKey = stringPreferencesKey("long_value_key")
        encryptedDataStore.putLong(longValueKey, longValue)
        val storedValue = encryptedDataStore.getLong(longValueKey, 0).first()
        assertEquals(longValue, storedValue)
    }

    @Test
    fun saveAndRestoreFloatValueShouldBeTheSame() = testScope.runTest {
        val floatValue = 4.2f
        val floatValueKey = stringPreferencesKey("float_value_key")
        encryptedDataStore.putFloat(floatValueKey, floatValue)
        val storedValue = encryptedDataStore.getFloat(floatValueKey, 0.0f).first()
        assertEquals(floatValue, storedValue)
    }

    @Test
    fun saveAndRestoreDoubleValueShouldBeTheSame() = testScope.runTest {
        val doubleValue = 4.1
        val doubleValueKey = stringPreferencesKey("double_value_key")
        encryptedDataStore.putDouble(doubleValueKey, doubleValue)
        val storedValue = encryptedDataStore.getDouble(doubleValueKey, 0.0).first()
        assertEquals(doubleValue, storedValue, 0.1)
    }

    @Test
    fun saveAndRestoreBooleanValueShouldBeTheSame() = testScope.runTest {
        val booleanValue = true
        val booleanValueKey = stringPreferencesKey("boolean_value_key")
        encryptedDataStore.putBoolean(booleanValueKey, booleanValue)
        val storedValue = encryptedDataStore.getBoolean(booleanValueKey, false).first()
        assertEquals(booleanValue, storedValue)
    }

    @Test
    fun afterClearingDataStoreShouldNotGetAnAlreadySavedValue() = testScope.runTest {
        val stringValue = "Hello"
        val stringValueKey = stringPreferencesKey("string_value_key")
        encryptedDataStore.putString(stringValueKey, stringValue)
        encryptedDataStore.clear()
        val storedValue = encryptedDataStore.getString(stringValueKey).first()
        assertNull(storedValue)
    }
}