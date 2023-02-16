package algorithm_examples;

import java.util.EmptyStackException;

/* Implement a MyQueue class which implements a queue using two stacks.  */

import java.util.Stack;

public class CustomQueue<T> {
	private Stack<T> head;
	private Stack<T> tail;

	public CustomQueue() {
		head = new Stack<T>();
		tail = new Stack<T>();
	}

	public void push(T item) {
		tail.add(item);
	}

	public T pop() {
		if (!head.isEmpty()) {
			return head.pop();
		}
		if (tail.isEmpty()) {
			return null;
		}

		while (!tail.isEmpty()) {
			T tmp = tail.pop();
			head.add(tmp);
		}

		return head.pop();

	}

	public static void main(String[] args) {
		CustomQueue<Integer> test = new CustomQueue<Integer>();
		test.push(5);
		test.push(4);
		test.push(1);
		System.out.println(test.pop());
		System.out.println(test.pop());
		System.out.println(test.pop());
	}
}
