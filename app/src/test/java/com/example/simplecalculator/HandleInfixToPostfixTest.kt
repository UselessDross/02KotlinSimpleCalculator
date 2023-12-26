package com.example.simplecalculator

import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows



class HandleInfixToPostfixTest {

    @Test
    fun testHandleInfixToPostfix() {
        val infixExpression = listOf("3", "+", "5", "*", "(", "2", "-", "8", ")")
        val expectedPostfix = listOf("3", "5", "2", "8", "-", "*", "+")

        val result = handleInfixToPostfix(infixExpression)

        assertEquals(expectedPostfix, result)
    }
    @Test
    fun testHandleInfixToPostfix_simpleExpression() {
        val infix = listOf("3", "+", "5", "*", "2")
        val expected = listOf("3", "5", "2", "*", "+")
        assertEquals(expected, handleInfixToPostfix(infix))
    }

    @Test
    fun testHandleInfixToPostfix_expressionWithParentheses() {
        val infix = listOf("(", "3", "+", "5", ")", "*", "2")
        val expected = listOf("3", "5", "+", "2", "*")
        assertEquals(expected, handleInfixToPostfix(infix))
    }

    @Test
    fun testHandleInfixToPostfix_expressionWithMultipleOperators() {
        val infix = listOf("3", "*", "(", "5", "+", "2", ")", "-", "4")
        val expected = listOf("3", "5", "2", "+", "*", "4", "-")
        assertEquals(expected, handleInfixToPostfix(infix))
    }


}
