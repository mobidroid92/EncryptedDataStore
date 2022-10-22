package com.bdour.encrypteddatastore.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun `null object returns null when converted toString`() {
        val nullInt: Int? = null
        val nullLong: Long? = null
        val nullFloat: Float? = null
        val nullDouble: Double? = null
        val nullBoolean: Boolean? = null

        assertNull(nullInt.toStringOrNull())
        assertNull(nullLong.toStringOrNull())
        assertNull(nullFloat.toStringOrNull())
        assertNull(nullDouble.toStringOrNull())
        assertNull(nullBoolean.toStringOrNull())
    }

    @Test
    fun `Int value returns correct String value when converted toString`() {
        val intValue = 1
        assertEquals("1", intValue.toStringOrNull())
    }

    @Test
    fun `Long value returns correct String value when converted toString`() {
        val longValue = 1L
        assertEquals("1", longValue.toStringOrNull())
    }

    @Test
    fun `Float value returns correct String value when converted toString`() {
        val floatValue = 1.0f
        assertEquals("1.0", floatValue.toStringOrNull())
    }

    @Test
    fun `Double value returns correct String value when converted toString`() {
        val doubleValue = 1.0
        assertEquals("1.0", doubleValue.toStringOrNull())
    }

    @Test
    fun `Boolean true value returns correct String value when converted toString`() {
        val booleanTrueValue = true
        assertEquals("true", booleanTrueValue.toStringOrNull())
    }

    @Test
    fun `Boolean false value returns correct String value when converted toString`() {
        val booleanFalseValue = false
        assertEquals("false", booleanFalseValue.toStringOrNull())
    }

}