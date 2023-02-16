package algorithm_examples;

import java.util.*;

/* 
	Algorithms for converting normal expression like " 5+7*9" to postfix like "579*+" 
	and execute postfix expression and return result like "579*+ --> 68"
	and parse strings to see if all open braces have close bracess like "({hello()}) --> true, ({]) --> false"

*/

public class InfıxAlgorithm {
	private static final String OPERAND = "+-*%";
	private static final String OPENINGBRACES = "[({";
	private static final HashMap<Character, Character> MATCHES = new HashMap<Character, Character>();

	public InfıxAlgorithm() {
		MATCHES.put(']', '[');
		MATCHES.put(')', '(');
		MATCHES.put('}', '{');
	}

	public static String InfixToPostfix(String value) {
		StringBuilder builder = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		char top;
		for (char element : value.toCharArray()) {
			if (isOperand(element)) {
				if (stack.empty()) {
					stack.push(element);
					continue;
				}
				top = stack.peek();
				if (top > element) {
					// if operand on top of stack has greater öncelik than pop all stack
					builder.append(stackPopper(stack));
					stack.push(element);
				} else {
					// if operand on top of stack has lesser öncelik than add operand to stack
					stack.push(element);
				}
				continue;
			}
			builder.append(element);
		}
		builder.append(stackPopper(stack));
		return builder.toString();
	}

	private static boolean isOperand(char element) {
		for (char operand : OPERAND.toCharArray()) {
			if (element == operand) {
				return true;
			}
		}
		return false;

	}

	private static String stackPopper(Stack<Character> stack) {
		StringBuilder tmp = new StringBuilder();
		while (!stack.isEmpty()) {
			tmp.append(stack.pop());
		}

		return tmp.toString();
	}

	public static int executePostfix(String exp) {
		Stack<Integer> stack = new Stack<Integer>();
		int firstValue;
		int secondValue;
		for (char element : exp.toCharArray()) {
			if (isOperand(element)) {
				firstValue = stack.pop();
				secondValue = stack.pop();
				stack.push(make(element, firstValue, secondValue));
				continue;
			}
			stack.push(Character.getNumericValue(element));
		}
		return stack.pop();
	}

	private static int make(char operand, int firstValue, int secondValue) {
		if (operand == '+') {
			return firstValue + secondValue;
		} else if (operand == '-') {
			return firstValue - secondValue;
		} else if (operand == '*') {
			return firstValue * secondValue;
		} else {
			return (int) (firstValue / secondValue);
		}
	}

	public static boolean isHole(String value) {
		Stack<Character> stack = new Stack<Character>();
		char top;
		for (char element : value.toCharArray()) {
			if (isOpeningBrace(element)) {
				stack.push(element);
				continue;
			}
			top = stack.peek();
			try {
				if (MATCHES.get(element) != top) {
					return false;
				}
				stack.pop();

			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		}
		if (!stack.empty()) {
			return false;
		}
		return true;
	}

	private static boolean isOpeningBrace(char element) {
		for (char openinBrace : OPENINGBRACES.toCharArray()) {
			if (openinBrace == element) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		InfıxAlgorithm hello = new InfıxAlgorithm();
		String exp = "5+8*9";
		String test = InfixToPostfix(exp);
		System.out.println(test);
		int result = executePostfix(test);
		System.out.println(result);
		System.out.println(isHole("[({s})]"));
	}
}
