package com.bdour.encrypteddatastore.encryption

interface EncryptionHelper {

    fun encryptData(rawData: String): String

    fun decryptData(encryptedData: String): String

}