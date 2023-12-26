package com.example.simplecalculator

import org.junit.Test


class HandleEvaluatePostfixTest {

    @Test
    fun testHandleEvaluatePostfix_SimpleAddition() {
        val postfix = listOf("3", "5", "+")
        val result = handleEvaluatePostfix(postfix)
        if (result == 8.0) {
            println("Test Passed: Simple Addition")
        } else {
            println("Test Failed: Simple Addition. Expected 8.0, but got $result")
        }
    }
@Test
    fun testHandleEvaluatePostfix_ComplexExpression() {
        val postfix = listOf("3", "5", "2", "*", "+")
        val result = handleEvaluatePostfix(postfix)
        if (result == 13.0) {
            println("Test Passed: Complex Expression")
        } else {
            println("Test Failed: Complex Expression. Expected 13.0, but got $result")
        }
    }
    @Test
    fun testHandleEvaluatePostfix_DivisionByZero() {
        val postfix = listOf("3", "0", "/")
        try {
            handleEvaluatePostfix(postfix)
            println("Test Failed: Division By Zero. Expected IllegalArgumentException.")
        } catch (e: IllegalArgumentException) {
            if (e.message == "Error in evaluatePostfix(): Error performing Operation at handlePerformOperation(): / by zero") {
                println("Test Passed: Division By Zero")
            } else {
                println("Test Failed: Division By Zero. Unexpected exception message: ${e.message}")
            }
        }
    }
    @Test
    fun testHandleEvaluatePostfix_InvalidOperator() {
        val postfix = listOf("3", "5", "2", "&", "+")
        try {
            handleEvaluatePostfix(postfix)
            println("Test Failed: Invalid Operator. Expected IllegalArgumentException.")
        } catch (e: IllegalArgumentException) {
            if (e.message == "Error in evaluatePostfix(): Invalid operator: &") {
                println("Test Passed: Invalid Operator")
            } else {
                println("Test Failed: Invalid Operator. Unexpected exception message: ${e.message}")
            }
        }
    }
    @Test
    fun testHandleEvaluatePostfix_EmptyExpression() {
        val postfix = emptyList<String>()
        try {
            val result = handleEvaluatePostfix(postfix)
            println("Test Failed: Empty Expression. Expected IllegalArgumentException.")
        } catch (e: IllegalArgumentException) {
            if (e.message == "Error in evaluatePostfix(): Expression is empty") {
                println("Test Passed: Empty Expression")
            } else {
                println("Test Failed: Empty Expression. Unexpected exception message: ${e.message}")
            }
        }
    }

    @Test
    fun testHandleEvaluatePostfix_SingleNumber() {
        val postfix = listOf("7")
        val result = handleEvaluatePostfix(postfix)
        if (result == 7.0) {
            println("Test Passed: Single Number")
        } else {
            println("Test Failed: Single Number. Expected 7.0, but got $result")
        }
    }

    @Test
    fun testHandleEvaluatePostfix_AdditionWithNegativeNumbers() {
        val postfix = listOf("-3", "-5", "+")
        val result = handleEvaluatePostfix(postfix)
        if (result == -8.0) {
            println("Test Passed: Addition with Negative Numbers")
        } else {
            println("Test Failed: Addition with Negative Numbers. Expected -8.0, but got $result")
        }
    }


}
