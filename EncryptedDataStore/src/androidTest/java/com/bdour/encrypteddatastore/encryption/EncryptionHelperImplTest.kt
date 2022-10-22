package com.bdour.encrypteddatastore.encryption

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class EncryptionHelperImplTest {

    private lateinit var encryptionHelperImpl: EncryptionHelperImpl

    @Before
    fun setup() {
        encryptionHelperImpl = EncryptionHelperImpl()
    }

    @Test
    fun whenEncryptSimpleStringItShouldBeDecryptedWithTheSameValue() {
        val string = "Hello"
        val encryptedString = encryptionHelperImpl.encryptData(string)
        assertEquals(string, encryptionHelperImpl.decryptData(encryptedString))
    }

    @Test
    fun whenEncryptIntOrLongStringItShouldBeDecryptedWithTheSameValue() {
        val intOrLongString = "1"
        val encryptedIntOrLongString = encryptionHelperImpl.encryptData(intOrLongString)
        assertEquals(intOrLongString, encryptionHelperImpl.decryptData(encryptedIntOrLongString))
    }

    @Test
    fun whenEncryptFloatOrDoubleStringItShouldBeDecryptedWithTheSameValue() {
        val floatOrDoubleString = "1.0"
        val encryptedFloatOrDoubleString = encryptionHelperImpl.encryptData(floatOrDoubleString)
        assertEquals(floatOrDoubleString, encryptionHelperImpl.decryptData(encryptedFloatOrDoubleString))

    }

    @Test
    fun whenEncryptBooleanTrueStringItShouldBeDecryptedWithTheSameValue() {
        val booleanTrueString = "true"
        val encryptedBooleanTrueString = encryptionHelperImpl.encryptData(booleanTrueString)
        assertEquals(booleanTrueString, encryptionHelperImpl.decryptData(encryptedBooleanTrueString))
    }

    @Test
    fun whenEncryptBooleanFalseStringItShouldBeDecryptedWithTheSameValue() {
        val booleanFalseString = "false"
        val encryptedBooleanFalseString = encryptionHelperImpl.encryptData(booleanFalseString)
        assertEquals(booleanFalseString, encryptionHelperImpl.decryptData(encryptedBooleanFalseString))
    }

    @Test
    fun whenEncryptEmptyStringItShouldBeDecryptedWithTheSameValue() {
        val emptyString = ""
        val encryptedEmptyString = encryptionHelperImpl.encryptData(emptyString)
        assertEquals(emptyString, encryptionHelperImpl.decryptData(encryptedEmptyString))
    }

    @Test
    fun whenEncryptBlankStringItShouldBeDecryptedWithTheSameValue() {
        val blankString = " "
        val encryptedBlankString = encryptionHelperImpl.encryptData(blankString)
        assertEquals(blankString, encryptionHelperImpl.decryptData(encryptedBlankString))
    }

    @Test
    fun whenEncryptSpecialCharsStringItShouldBeDecryptedWithTheSameValue() {
        val specialCharsString = "!@#$%^&*(){}"
        val encryptedSpecialCharsString = encryptionHelperImpl.encryptData(specialCharsString)
        assertEquals(specialCharsString, encryptionHelperImpl.decryptData(encryptedSpecialCharsString))
    }

    @Test
    fun whenEncryptArabicStringItShouldBeDecryptedWithTheSameValue() {
        val arabicString = "مرحبا ١٢٣٤٥٦٧٨٩٠"
        val encryptedArabicString = encryptionHelperImpl.encryptData(arabicString)
        assertEquals(arabicString, encryptionHelperImpl.decryptData(encryptedArabicString))
    }

}