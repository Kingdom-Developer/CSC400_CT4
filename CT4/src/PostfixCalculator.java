/**
 * 
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 */
public class PostfixCalculator {
	Stack<Integer> stack;
	
	public PostfixCalculator() {
		this.stack = new Stack<Integer>();
	}
	
	public int evaluatePostfix(String postfixExpression) throws IllegalArgumentException {
        // Your implementation here
		int operand1;
		int operand2;
		
		StringBuilder digit = new StringBuilder();
		
		// Ensure stack is empty
		stack.clear();
				
		for (Character token : postfixExpression.toCharArray()) {
			if (Character.isDigit(token)) {
				digit.append(token);
			}
			else if (token == ' ') {
				if (digit.length() > 0) {
					stack.push(Integer.valueOf(digit.toString()));
					digit.setLength(0);				
				}
			}
			else {
				if (stack.size() < 2) {
					throw new IllegalArgumentException("Invalid expression: Incorrect number of operands");
				}
				operand2 = stack.pop();
				operand1 = stack.pop();
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
						stack.push(operand1 / operand2);
						break;
					
					case '%':
						stack.push(operand1 % operand2);
						break;
					default:
						throw new IllegalArgumentException("Invalid expression: Incorrect operator");
				}
			}	
		}

		if (stack.size() != 1) {
			throw new IllegalArgumentException("Invalid expression: Unable to evaluate");
		}
        return stack.pop(); // Placeholder
    }
	
	public void evaluateFromFile(String filename) {
		try {
			File file = new File(filename);
			Scanner scnr = new Scanner(file);
			
			while (scnr.hasNextLine()) {
				String expression = scnr.nextLine();
				
				try {
					int result = evaluatePostfix(expression);
					System.out.println("Expression: " + expression + " = " + result);
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
	
    public static void main(String[] args) {
        PostfixCalculator calculator = new PostfixCalculator();
        
        
        
        calculator.evaluateFromFile("expressions.txt");
        
        // Example 1: Valid Expression
        try {
        	String expression1 = "42 2 * 3 +";
            System.out.println("Result 1: " + calculator.evaluatePostfix(expression1));
        }
        catch (IllegalArgumentException e) {
        	System.out.println(e.getMessage());
        }
        
        // Example 2: Valid Expression
        try {
            String expression2 = "5 3 + 7 *";
            System.out.println("Result 2: " + calculator.evaluatePostfix(expression2));
        }
        catch (IllegalArgumentException e) {
        	System.out.println(e.getMessage());
        }
        
        // Example 3: Invalid Expression
        try {
        	String expression3 = "42*+"; // Missing operand
            System.out.println("Result 3: " + calculator.evaluatePostfix(expression3));
        }
        catch (IllegalArgumentException e) {
        	System.out.println(e.getMessage());
        }
    }	        
}














//for (int i = 0; i < postfixExpression.length(); i++) {
//if (Character.isDigit(postfixExpression.charAt(i))) {
//	digit.append(postfixExpression.charAt(i));
//}
//else if (postfixExpression.charAt(i) == ' ') {
//	stack.push(Integer.valueOf(digit.toString()));
//	digit.setLength(0);
//}
//else {
////	if (digit.length() > 0) {
////        stack.push(Integer.parseInt(digit.toString()));
////        digit.setLength(0);
////    }
//	if (stack.size() < 2) {
//		throw new IllegalArgumentException("Invalid expression: Incorrect number of operands");
//	}
//	operand2 = stack.pop();
//	operand1 = stack.pop();
//	switch (postfixExpression.charAt(i)) {
//		case '+':
//			stack.push(operand1 + operand2);
//			break;
//
//		case '-':
//			stack.push(operand1 - operand2);
//			break;
//			
//		case '*':
//			stack.push(operand1 * operand2);
//			break;
//		
//		case '/':
//			stack.push(operand1 / operand2);
//			break;
//		
//		case '%':
//			stack.push(operand1 % operand2);
//			break;
//		default:
//			throw new IllegalArgumentException("Invalid expression: Incorrect operator");
//	}
//}	
//}