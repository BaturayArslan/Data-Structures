package algorithm_examples;

import java.util.*;

/*
	Stack Min: How would you design a stack which, in addition to push and pop, has a function min
	which returns the minimum element? Push, pop and min should all operate in 0(1) time.
*/

public class StackMin<T extends Integer> {
	private static class Node<T> {
		private T data;
		private Node<T> next;
		private T minData;

		public Node(T data, T minData) {
			this.data = data;
			this.minData = minData;
		}
	}

	private Node<T> top;

	public void pop() {
		if (top == null) {
			throw new EmptyStackException();
		}

		top = top.next;
	}

	public T peek() {
		if (top == null) {
			throw new EmptyStackException();
		}
		return top.data;
	}

	public void push(T item) {
		if (top == null) {
			Node<T> node = new Node<T>(item, item);
			top = node;
		}

		Node<T> node = new Node<T>(item, item < top.minData ? item : top.minData);
		node.next = top;
		top = node;

	}

	public T getMin() {
		return top.minData;
	}

	public static void main(String[] args) {
		StackMin<Integer> test = new StackMin<Integer>();
		test.push(4);
		test.push(2);
		test.push(3);
		test.push(1);
		test.push(5);
		System.out.println(test.getMin());
		test.pop();
		test.pop();
		System.out.println(test.getMin());

	}
}
