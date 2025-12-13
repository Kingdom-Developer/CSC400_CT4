package com.CT4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PostfixCalculatorTest {
    PostfixCalculator calculator = new PostfixCalculator();

    @Test
    void testValidExpression1() {
        String expression1 = "42 2 * 3 +";
        int result = calculator.evaluatePostfix(expression1);
        System.out.println("Result 1: " + result);
        assertEquals(87, calculator.evaluatePostfix(expression1));
    }

    @Test
    void testValidExpression2() {
        String expression2 = "5 3 + 7 *";
        int result = calculator.evaluatePostfix(expression2);
        System.out.println("Result 2: " + result);
        assertEquals(56, calculator.evaluatePostfix(expression2));
    }

    @Test
    void testInvalidExpression_MissingOperand() {
        String expression3 = "4 2 * +";
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.evaluatePostfix(expression3);
        });
    }

    @Test
    void testInvalidExpression_Empty() {
        String expression4 = "";
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.evaluatePostfix(expression4);
        });
    }

    @Test
    void testInvalidExpression_Null() {
    	String expression5 = null;
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.evaluatePostfix(expression5);
        });
    }

    @Test
    void testEvaluateFromFile() {
        assertDoesNotThrow(() -> calculator.evaluateFromFile("expressions.txt"));
    }
}

