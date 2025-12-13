package com.CT4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * A calculator that evaluates postfix expressions.
 * <p>
 * This class allows for a text file of expressions to be evaluated and
 * printed to the console.
 * </p>
 *
 * @author Nolan_Hill
 */

public class PostfixCalculator {
	/** Stack that is used to store operands during evaluation and final result */
	private Stack<Integer> stack;
	
	/** Constructs a new calculator with an empty stack */
	public PostfixCalculator() {
		this.stack = new Stack<Integer>();
	}
	
	/** 
	 * Evaluates a postfix expression and returns the result.
	 * <p>
	 * Only works for addition (+), subtraction (-), multiplication (*), 
	 * division (/), and modulus (%).
	 *  </p>
	 *  
	 * @param postfixExpression the postfix expression to evaluate
	 * @return the integer result of evaluating the expression
     * @throws IllegalArgumentException if the expression is invalid
	 */
	public int evaluatePostfix(String postfixExpression) throws IllegalArgumentException {
		// Check if expression is null
		if (postfixExpression == null) {
			throw new IllegalArgumentException("Invalid expression: Null expression");
		}
		
		// Check if expression is empty
		if (postfixExpression.trim().length() == 0) {
			throw new IllegalArgumentException("Invalid expression: Empty expression");
		}
		
		int operand1;
		int operand2;
		
		// StringBuilder to store each digit for multi-digit operands
		StringBuilder number = new StringBuilder();
		
		// Ensure stack is empty before evaluating expression
		stack.clear();
		
		// Iterate through each character in expression
		for (Character token : postfixExpression.toCharArray()) {
			if (Character.isDigit(token)) {
				// Use StringBuilder to build multi-digit operands
				number.append(token);
			}
			// When token is a space, the number is ready to be pushed to stack
			else if (token == ' ') {
				// Push the built number to stack
				if (number.length() > 0) {
					stack.push(Integer.valueOf(number.toString()));
					// Reset for next number
					number.setLength(0);				
				}
			}
			else {
				// Ensure there are two operands
				if (stack.size() < 2) {
					throw new IllegalArgumentException("Invalid expression: Incorrect number of operands");
				}
				
				// Assign popped values to temporary operand variables
				operand2 = stack.pop();
				operand1 = stack.pop();
				
				// Determine which operation to perform on operands
				switch (token) {
					case '+':
						stack.push(operand1 + operand2);
						break;
					case '-':
						stack.push(operand1 - operand2);
						break;
					case '*':
						stack.push(operand1 * operand2);
						break;
					case '/':
						if (operand2 == 0) {
							throw new IllegalArgumentException("Cannot divide by zero");
						}
						stack.push(operand1 / operand2);
						break;
					case '%':
						stack.push(operand1 % operand2);
						break;
					default:
						// Exception for incorrect type of operator
						throw new IllegalArgumentException("Invalid expression: Incorrect operator");
				}
			}	
		}
		
		// Ensure there is no missing operator at the end as signified by remaining digits in StringBuilder object
		if (number.length() > 0) {
			throw new IllegalArgumentException("Invalid expression: missing an operator");
		}

		// Ensure the result is stored in stack
		if (stack.size() != 1) {
			throw new IllegalArgumentException("Invalid expression: Unable to evaluate");
		}
		
        return stack.pop(); 
    }
	
	/**
     * Reads postfix expressions from a file and prints results to console.
     * <p>
     * If postfix expression in file is invalid, it will print exceptions to screen.
     * </p>
     *
     * @param filename the file name that contains postfix expressions
     */
	public void evaluateFromFile(String filename) {
		// Temporary string variable to hold expression on each line
		String expression;
		
		try {
			File file = new File(filename);
			Scanner scnr = new Scanner(file);
			
			// Read each line of the file and evaluate its expression
			while (scnr.hasNextLine()) {
				// Store expression to temporary variable
				expression = scnr.nextLine();
				
				try {
					// Call on evaluatePostfix() and store result to variable
					int result = evaluatePostfix(expression);
					System.out.println("File expression: " + expression + " = " + result);
				}
				catch (IllegalArgumentException e) {
					System.out.println("Could not evaluate '" + expression + "' due to error: " + e.getMessage());
				}
			}
			
			scnr.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Invalid filename: " + filename);
		}
	}     
}