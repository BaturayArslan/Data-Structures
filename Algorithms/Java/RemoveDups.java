package algorithm_examples;

import java.util.*;

/*
	Remove Dups: Write code to remove duplicates from an unsorted linked list
*/

public class RemoveDups {
	private class Node {
		private int data;
		private Node nextNode;

		public Node(int data) {
			this.data = data;
		}
	}

	private Node head;

	public RemoveDups(int length) {
		Random rand = new Random();
		Node tmp = new Node(rand.nextInt(11));
		head = tmp;
		System.out.printf("%d ", tmp.data);

		for (int i = 0; i < length - 1; i++) {
			int number = rand.nextInt(11);
			tmp.nextNode = new Node(number);
			tmp = tmp.nextNode;
			System.out.printf("%d ", number);
		}

	}

	public static void removeDuplicate(Node root) {
		if (root == null) {
			return;
		}

		Node prevNode = null;
		Node tmp = root;
		HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
		while (tmp != null) {
			if (table.containsKey(tmp.data)) {
				prevNode.nextNode = tmp.nextNode;
				tmp = tmp.nextNode;
				continue;
			}
			table.put(tmp.data, tmp.data);
			prevNode = tmp;
			tmp = tmp.nextNode;
		}

	}

	public Node getHead() {
		return this.head;
	}

	public void print() {
		Node tmp = head;
		while (tmp != null) {
			System.out.printf("%d ", tmp.data);
			tmp = tmp.nextNode;
		}
	}

	public static void main(String[] args) {
		RemoveDups test = new RemoveDups(10);
		RemoveDups.removeDuplicate(test.getHead());
		System.out.println("");
		test.print();
	}

}
