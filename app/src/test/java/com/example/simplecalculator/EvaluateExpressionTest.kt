package com.example.simplecalculator
import junit.framework.TestCase.assertEquals
import org.junit.Test


class EvaluateExpressionTest {

    @Test
    fun testEvaluateExpression_SimpleAddition() {
        val result = evaluateExpression("3 + 5")
        assertEquals(8.0, result)
    }

    @Test
    fun testEvaluateExpression_ComplexExpression() {
        val result = evaluateExpression("3 + 5 * 2")
        assertEquals(13.0, result)
    }





    @Test
    fun testEvaluateExpression_AdditionWithNegativeNumbers() {
        val result = evaluateExpression("-3 + (-5)")
        assertEquals(-8.0, result)
    }




    @Test
    fun testEvaluateExpression_Parentheses() {
        val result = evaluateExpression("(3 + 5) * 2")
        assertEquals(16.0, result)
    }

    // Add more test methods as needed

}
