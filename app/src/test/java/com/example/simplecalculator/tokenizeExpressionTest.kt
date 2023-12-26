package com.example.simplecalculator


import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class tokenizeExpressionTest {


    @Test
    fun testTokenizeExpression() {
        // Test valid expression
        val expression1 = "(2 + 6 / 2)"
        val result1 = tokenizeExpression(expression1)
        assertEquals(listOf("(", "2", "+", "6", "/", "2", ")"), result1)

    }
    @Test
    fun testTokenizeExpression_testingExpressionWithSpaces(){
        val expression = "  3 * ( 4 - 2 ) "
        val result = tokenizeExpression(expression)
        assertEquals(listOf("3", "*", "(", "4", "-", "2", ")"), result)
    }

    @Test
    fun testTokenizeExpression_testingEmptyExpression(){
        val expression = ""
        val result = tokenizeExpression(expression)
        assertEquals(emptyList<String>(), result)
    }


    @Test
    fun testTokenizeExpression_testingExpressionWithInvalidCharater(){
        val expression = "2x + 3"
        assertThrows(IllegalArgumentException::class.java) {
            tokenizeExpression(expression)
        }
    }


    @Test
    fun testTokenizeExpression_testingSingleDigit() {
        val expression = "5"
        val result = tokenizeExpression(expression)
        assertEquals(listOf("5"), result)
    }

    @Test
    fun testTokenizeExpression_testingMultipleDigits() {
        val expression = "123"
        val result = tokenizeExpression(expression)
        assertEquals(listOf("123"), result)
    }

    @Test
    fun testTokenizeExpression_testingMultipleOperators() {
        val expression = "2 + 3 * 4 / 2 - 1"
        val result = tokenizeExpression(expression)
        assertEquals(listOf("2", "+", "3", "*", "4", "/", "2", "-", "1"), result)
    }

    @Test
    fun testTokenizeExpression_testingExpressionWithParentheses() {
        val expression = "(5 + 3) * 2"
        val result = tokenizeExpression(expression)
        assertEquals(listOf("(", "5", "+", "3", ")", "*", "2"), result)
    }

    @Test
    fun testTokenizeExpression_testingExpressionWithWhitespace() {
        val expression = "  1  +   2   "
        val result = tokenizeExpression(expression)
        assertEquals(listOf("1", "+", "2"), result)
    }

    @Test
    fun testTokenizeExpression_testingExpressionWithInvalidCharacters() {
        val expression = "2x + 3"
        assertThrows(IllegalArgumentException::class.java) {
            tokenizeExpression(expression)
        }
    }

    @Test
    fun testTokenizeExpression_testingExpressionWithInvalidParentheses() {
        val expression = "(2 + 3"
        assertThrows(IllegalArgumentException::class.java) {
            tokenizeExpression(expression)
        }
    }

    @Test
    fun testTokenizeExpression_testingExpressionWithMismatchedParentheses() {
        val expression = "2 + (3 * 4"
        assertThrows(IllegalArgumentException::class.java) {
            tokenizeExpression(expression)
        }
    }

    @Test
    fun testTokenizeExpression_testingLeadingAndTrailingWhitespaces() {
        val expression = "  1 + 2   "
        val result = tokenizeExpression(expression)
        assertEquals(listOf("1", "+", "2"), result)
    }

    @Test
    fun testTokenizeExpression_testingComplexExpressionWithMultipleParentheses() {
        val expression = "((5 + 3) * 2) - (4 / (1 + 1))"
        val result = tokenizeExpression(expression)
        assertEquals(listOf("(", "(", "5", "+", "3", ")", "*", "2", ")", "-", "(", "4", "/", "(", "1", "+", "1", ")", ")"), result)
    }



    @Test
    fun testTokenizeExpression_testingZeroAsOperand() {
        val expression = "0 + 5"
        val result = tokenizeExpression(expression)
        assertEquals(listOf("0", "+", "5"), result)
    }



    @Test
    fun testTokenizeExpression_testingInvalidCharactersWithinDigits() {
        val expression = "1x23"
        assertThrows(IllegalArgumentException::class.java) {
            tokenizeExpression(expression)
        }
    }

    @Test
    fun testTokenizeExpression_testingMixOfValidAndInvalidCharacters() {
        val expression = "2a + b3"
        assertThrows(IllegalArgumentException::class.java) {
            tokenizeExpression(expression)
        }
    }

    @Test
    fun testTokenizeExpression_testingLargeNumbers() {
        val expression = "12345678901234567890"
        val result = tokenizeExpression(expression)
        assertEquals(listOf("12345678901234567890"), result)
    }

    @Test
    fun testTokenizeExpression_testingUnaryOperators() {
        // Test unary negation
        val expression1 = "-5"
        val result1 = tokenizeExpression(expression1)
        assertEquals(listOf("-", "5"), result1)

        // Test unary positive
        val expression2 = "+3"
        val result2 = tokenizeExpression(expression2)
        assertEquals(listOf("+", "3"), result2)
    }




}

