package com.example.simplecalculator

    import org.junit.Assert.assertEquals
    import org.junit.Assert.assertThrows
    import org.junit.Test

class GetPrecedenceTest {

    @Test
    fun testGetPrecedenceForAddition() {
        val result = getPrecedence("+")
        assertEquals(1, result)
    }

    @Test
    fun testGetPrecedenceForSubtraction() {
        val result = getPrecedence("-")
        assertEquals(1, result)
    }

    @Test
    fun testGetPrecedenceForMultiplication() {
        val result = getPrecedence("*")
        assertEquals(2, result)
    }

    @Test
    fun testGetPrecedenceForDivision() {
        val result = getPrecedence("/")
        assertEquals(2, result)
    }

    @Test
    fun testGetPrecedenceForOtherOperator() {
        val result = getPrecedence("%")
        assertEquals(2, result)
    }

    @Test
    fun testGetPrecedenceForEmptyString() {
        assertThrows(IllegalArgumentException::class.java) {
            getPrecedence("")
        }
    }

    @Test
    fun testGetPrecedenceForNullString() {
        assertThrows(IllegalArgumentException::class.java) {
            getPrecedence(null)
        }
    }

    @Test
    fun testGetPrecedenceForInvalidOperator() {
        assertThrows(IllegalArgumentException::class.java) {
            getPrecedence("invalid")
        }
    }
}
