package com.mycompany.datastructures;

/* Custom Stack implementation with Arrays */

interface MyStack<K> {
	void push(K item);

	void pop();

	K top();

	boolean isEmpty();
}

public class CustomStack<K> implements MyStack<K> {
	private int size = 16;
	private int cursor = -1;
	private K[] list;

	public CustomStack() {
		list = (K[]) new Object[size];
	}

	private void doubleSize() {
		size *= 2;
		K[] newList = (K[]) new Object[size];
		int counter = 0;
		for (K element : list) {
			newList[counter] = element;
			counter++;
		}

		list = newList;
	}

	public void push(K item) {
		if (cursor + 1 >= size) {
			// list is empty double list
			doubleSize();
		}

		cursor++;
		list[cursor] = item;

	}

	public boolean isEmpty() {
		if (cursor == -1) {
			return true;
		}
		return false;
	}

	public void pop() throws ArrayIndexOutOfBoundsException {
		if (isEmpty()) {
			// Throw An Error
			throw new ArrayIndexOutOfBoundsException();
		}
		cursor--;
	}

	public K top() throws ArrayIndexOutOfBoundsException {
		if (isEmpty()) {
			// Throw An Error
			throw new ArrayIndexOutOfBoundsException();
		}
		return list[cursor];
	}

	public static void main(String[] args) {
		CustomStack<Integer> test = new CustomStack();
		test.push(1);
		test.push(5);
		test.push(12);
		System.out.println(test.top());
		test.pop();
		System.out.println(test.top());
		test.pop();
		test.pop();

	}
}
