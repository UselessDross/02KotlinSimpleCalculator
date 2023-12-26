package com.example.simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.simplecalculator.ui.theme.SimpleCalculatorTheme
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}


@Preview
@Composable
fun CalculatorUi(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .wrapContentSize()
    ) {
        var output by remember { mutableStateOf("") }

        val EqualSign:       String = "="
        val ClearScreenSign: String = "AC"
        val deleteSign:      String = "del"
        val onButtonPressed: (String) -> Unit = { pressedText ->
            when (pressedText) {
                EqualSign -> {
                    try {
                        output = evaluateExpression(output).toString()
                    } catch (e: Exception) {
                        output = "Error"
                    }
                }
                ClearScreenSign -> {
                    output = ""
                }
                deleteSign -> {
                    output = output.dropLast(1)
                }
                else -> {
                    output = output.plus(pressedText)
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
                .align(Alignment.CenterHorizontally)
                .background(Color.Black)
                .border(12.dp, Color.DarkGray)
                .padding(16.dp)
        ) {
            Text(
                text = output,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }

        ButtonRow(ClearScreenSign,"(",")",  "/",onButtonPressed, Color(0xff957e72),Color.DarkGray,Color.DarkGray)
        ButtonRow("1",    "2","3",  "*",onButtonPressed)
        ButtonRow("3",    "4","5",  "-",onButtonPressed)
        ButtonRow("6",    "8","9",  "+",onButtonPressed)
        ButtonRow("0",    ".", deleteSign,      EqualSign,onButtonPressed, ButtonColor03 = Color(0xff957e72))
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .weight(0.005f)){}
    }
}



@Composable
fun ButtonRow(wholeLeft: String,
              midLeft:   String,
              midRight:  String,
              wholeRight:String,
              onButtonPressed: (String) -> Unit,
              ButtonColor01: Color = Color(0xf0c1afa0),
              ButtonColor02: Color = Color(0xf0c1afa0),
              ButtonColor03: Color = Color(0xf0c1afa0),
              ButtonColor04: Color = Color.DarkGray
              ){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
        Button(onClick = { onButtonPressed(wholeLeft) },
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 6.dp, 6.dp), shape = RoundedCornerShape(3.dp), colors = ButtonDefaults.buttonColors(ButtonColor01)) {Text(wholeLeft, fontSize = 24.sp)}
        Button(onClick = { onButtonPressed(midLeft)   },
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 0.dp, 6.dp), shape = RoundedCornerShape(3.dp),colors = ButtonDefaults.buttonColors(ButtonColor02)) {Text(midLeft   , fontSize = 24.sp)  }
        Button(onClick = { onButtonPressed(midRight)  },
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(6.dp, 0.dp, 0.dp, 6.dp), shape = RoundedCornerShape(3.dp),colors = ButtonDefaults.buttonColors(ButtonColor03)) {Text(midRight  , fontSize = 24.sp) }
        Button(onClick = { onButtonPressed(wholeRight)},
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(6.dp, 0.dp, 0.dp, 6.dp), shape = RoundedCornerShape(3.dp),colors = ButtonDefaults.buttonColors(ButtonColor04)) {Text(wholeRight, fontSize = 24.sp)}
    }
}


fun evaluateExpression(expression: String): Double {
    try {
        val tokens = tokenizeExpression(expression)
        val postfix = handleInfixToPostfix(tokens)
        return handleEvaluatePostfix(postfix)
        } catch (e: Exception) {
                                throw IllegalArgumentException("Error evaluating expression in evaluateExpression(): ${e.message}", e)
                               }
}


// fully tested
fun tokenizeExpression(expression: String): List<String> {
    try {
        val operators = setOf("(", ")", "+", "-", "*", "/")

        // Check for mismatched parentheses
        val openParenCount = expression.count { it == '(' }
        val closeParenCount = expression.count { it == ')' }

        if (openParenCount != closeParenCount) {
            throw IllegalArgumentException("Mismatched parentheses in the expression")
        }

        val pattern = "\\s*(${operators.map(Regex::escape).joinToString("|")})\\s*|\\b-?\\d+\\b"

        val matches = pattern.toRegex().findAll(expression)

        val result = matches.map { it.value.trim() }.toList()

        if (result.joinToString("") != expression.replace(" ", "")) {
            throw IllegalArgumentException("Invalid character in the expression")
        }

        return result
    } catch (e: Exception) {
        throw IllegalArgumentException("Error processing expression at tokenizeExpression(): ${e.message}")
    }
}



// fully tested
fun getPrecedence(operator: String?): Int {
    if (operator.isNullOrEmpty()) {
        throw IllegalArgumentException("Operator string cannot be null or empty")
    }

    return when (operator) {
        "+", "-" -> 1
        "*", "/", "%" -> 2  // Assign the same precedence to * / and %
        "u-" -> 3 // Precedence for unary minus
        "(" -> 0  // Opening parenthesis has the lowest precedence
        else -> throw IllegalArgumentException("Invalid operator: $operator")
    }
}



// fully tested
// fully tested
fun handleInfixToPostfix(infix: List<String>): List<String> {
    try {
        val outputStack = mutableListOf<String>()
        val operatorStack = mutableListOf<String>()

        for (i in infix.indices) {
            val token = infix[i]
            when {
                token.matches(Regex("\\d+")) -> outputStack.add(token)
                token == "(" -> operatorStack.add(token)
                token == ")" -> {
                    while (operatorStack.isNotEmpty() &&
                        operatorStack.last() != "("
                    ) {
                        outputStack.add(operatorStack.removeAt(operatorStack.lastIndex))
                    }
                    operatorStack.removeAt(operatorStack.lastIndex)
                }
                else -> {
                    if (token == "-" && (i == 0 || infix[i - 1] == "(")) {
                        // Handle unary minus
                        operatorStack.add("u-")
                    } else {
                        while (operatorStack.isNotEmpty() &&
                            operatorStack.last() != "(" &&
                            (token != "+" || operatorStack.last() != "+") &&
                            getPrecedence(token) <= getPrecedence(operatorStack.last())
                        ) {
                            outputStack.add(operatorStack.removeAt(operatorStack.lastIndex))
                        }
                        operatorStack.add(token)
                    }
                }
            }
        }

        while (operatorStack.isNotEmpty()) {
            outputStack.add(operatorStack.removeAt(operatorStack.lastIndex))
        }

        return outputStack
    } catch (e: Exception) {
        throw IllegalArgumentException("Error processing at handleInfixToPostfix(): ${e.message}", e)
    }
}


// fully tested
fun handlePerformOperation(a: Double, b: Double, operator: String): Double {
    try {
        return when (operator) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> {
                if (b == 0.0) {
                    throw IllegalArgumentException("Error performing Operation at handlePerformOperation(): / by zero")
                }
                a / b
            }
            else -> throw IllegalArgumentException("Invalid operator: $operator")
        }
    } catch (e: IllegalArgumentException) {
        throw e // Re-throw the IllegalArgumentException without appending additional information
    } catch (e: Exception) {
        throw IllegalArgumentException("Error performing Operation at handlePerformOperation(): ${e.message}")
    }
}


// fully tested
// fully tested
// fully tested
// fully tested
fun handleEvaluatePostfix(postfix: List<String>): Double {
    try {
        val stack = mutableListOf<Double>()

        for (token in postfix) {
            if (token.matches(Regex("-?\\d+"))) {
                stack.add(token.toDouble())
            } else {
                when (token) {
                    "u-" -> {
                        if (stack.isNotEmpty()) {
                            stack[stack.lastIndex] = -stack[stack.lastIndex]
                        } else {
                            throw IllegalArgumentException("Error in evaluatePostfix(): Insufficient operands for operator: $token")
                        }
                    }
                    else -> {
                        if (stack.size < 2) {
                            throw IllegalArgumentException("Error in evaluatePostfix(): Insufficient operands for operator: $token. Stack: $stack")
                        }
                        val b = stack.removeAt(stack.lastIndex)
                        val a = stack.removeAt(stack.lastIndex)
                        stack.add(handlePerformOperation(a, b, token))
                    }
                }
            }
        }

        if (stack.size != 1) {
            throw IllegalArgumentException("Error in evaluatePostfix(): Invalid expression. Stack: $stack")
        }

        return stack.first()
    } catch (e: Exception) {
        throw IllegalArgumentException("Error in evaluatePostfix(): ${e.message}", e)
    }
}
