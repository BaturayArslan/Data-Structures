package com.mycompany.datastructures;

import java.util.*;

interface MyLinkedList {
	void insert(int index, Object value);

	void push(Object value);

	void delete(int index);

	Object get(int index);

	Object[] getAll();

	void set(int index, Object value);

	void reverse();

	int length();

}

public class CustomLinkedList implements MyLinkedList {
	private Node head;
	private int length = 0;

	public CustomLinkedList() {
		head = new Node(null, null);
	}

	public void push(Object value) {
		Node lastNode = getLastNode();
		Node newNode = new Node(value, null);
		lastNode.setNext(newNode);
		length++;
	}

	public void insert(int index, Object value) throws ArrayIndexOutOfBoundsException {
		// Our index sould be 0 <= index < length and length != 0
		if (length == 0) {
			throw new ArrayIndexOutOfBoundsException();
		}

		Node prevNode = getNode(index - 1);
		Node newNode = new Node(value, prevNode.getNext());
		prevNode.setNext(newNode);

	}

	private Node getNode(int index) throws ArrayIndexOutOfBoundsException {
		if (index < length && index >= 0) {
			int counter = 0;
			Node tmp = head;
			while (counter <= index) {
				tmp = tmp.getNext();
				counter++;
			}
			return tmp;
		} else if (index == -1) {
			return head;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	private Node getLastNode() {
		Node tmp = head;
		while (tmp.getNext() != null) {
			tmp = tmp.getNext();
		}
		return tmp;
	}

	public Object get(int index) throws ArrayIndexOutOfBoundsException {
		return getNode(index).getData();
	}

	public Object[] getAll() {
		Object[] arr = new Object[length];
		int counter = 0;
		Node tmp = head;
		while (tmp.getNext() != null) {
			tmp = tmp.getNext();
			arr[counter] = tmp.getData();
			counter++;
		}
		return arr;
	}

	public void delete(int index) {
		Node prevNode = getNode(index - 1);
		Node currentNode = prevNode.getNext();
		prevNode.setNext(currentNode.getNext());
	}

	public void set(int index, Object value) {
		Node currentNode = getNode(index);
		currentNode.setData(value);
	}

	public void reverse() {
		if (head.getNext() == null) {
			return;
		}

		Node tmp = head.getNext();
		Node prev = null;
		Node next = tmp.getNext();

		while (next != null) {
			tmp.setNext(prev);
			prev = tmp;
			tmp = next;
			next = next.getNext();
		}
		tmp.setNext(prev);
		head.setNext(tmp);
	}

	public int length() {
		return length;
	}

	public void removeDuplicate() {
		HashSet<Integer> hashtable = new HashSet<Integer>();
		Node first = head.getNext();
		Node second = head;
		int data;
		while (first != null) {
			data = (int) first.getData();
			if (!hashtable.add(data)) {
				// duplication.. delete node
				second.setNext(first.getNext());
				first = first.getNext();
				length--;
				continue;
			}
			first = first.getNext();
			second = second.getNext();

		}
	}

	public Object[] toLast(int index) {
		Node tmp = head.getNext();
		Object[] result = new Object[length - index];
		int loopCounter = 0;
		int resultCounter = 0;

		while (tmp != null) {
			if (loopCounter >= index) {
				result[resultCounter] = tmp.getData();
				resultCounter++;
			}
			tmp = tmp.getNext();
			loopCounter++;
		}

		return result;
	}

	public Object getMiddle() {
		int index = (int) (length / 2);
		if (length <= 2) {
			return null;
		}
		Node node = getNode(index);
		return node.getData();

	}

	public static void main(String[] args) {
		CustomLinkedList test = new CustomLinkedList();
		test.push(5);
		test.push(3);
		test.push(4);
		test.push(5);
		test.push(10);

		Object[] values = test.getAll();
		for (Object element : values) {
			System.out.println(element.toString());
		}

		test.reverse();
		values = test.getAll();
		for (Object element : values) {
			System.out.println(element.toString());
		}

		test.removeDuplicate();
		values = test.getAll();
		for (Object element : values) {
			System.out.println(element.toString());
		}

		values = test.toLast(2);
		for (Object element : values) {
			System.out.println(element.toString());
		}

	}

}
