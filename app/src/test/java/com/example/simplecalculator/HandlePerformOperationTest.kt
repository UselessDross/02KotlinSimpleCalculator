package com.example.simplecalculator

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows

class HandlePerformOperationTest {

    @Test
    fun testHandlePerformOperation_Addition() {
        val result = handlePerformOperation(3.0, 5.0, "+")
        assertEquals(8.0, result, 0.001)
    }

    @Test
    fun testHandlePerformOperation_Subtraction() {
        val result = handlePerformOperation(7.0, 4.0, "-")
        assertEquals(3.0, result, 0.001)
    }

    @Test
    fun testHandlePerformOperation_Multiplication() {
        val result = handlePerformOperation(2.0, 6.0, "*")
        assertEquals(12.0, result, 0.001)
    }

    @Test
    fun testHandlePerformOperation_Division() {
        val result = handlePerformOperation(8.0, 4.0, "/")
        assertEquals(2.0, result, 0.001)
    }

    @Test
    fun testHandlePerformOperation_InvalidOperator() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            handlePerformOperation(3.0, 5.0, "%") // Using an invalid operator
        }
        assertEquals("Invalid operator: %", exception.message)
    }

    @Test
    fun testHandlePerformOperation_DivisionByZero() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            handlePerformOperation(3.0, 0.0, "/") // Division by zero
        }
        assertEquals("Error performing Operation at handlePerformOperation(): / by zero", exception.message)
    }
}
