package algorithm_examples;

/*
	List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
	at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
*/

import java.util.*;
import java.lang.*;

public class ListOfDepth {
	private class Node {
		int data;
		Node leftNode;
		Node rightNode;

		public Node(int data) {
			this.data = data;
		}

		public Node add(int item, Node root) {
			if (root == null) {
				return new Node(item);
			}

			if (item < root.data) {
				root.leftNode = add(item, root.leftNode);
			} else {
				root.rightNode = add(item, root.rightNode);
			}

			return root;
		}
	}

	private Node root;

	public ListOfDepth() {

		root = new Node(14);
		root.add(7, root);
		root.add(8, root);
		root.add(1, root);
		root.add(16, root);
		root.add(15, root);
		root.add(20, root);
		root.add(12, root);
	}

	public static ArrayList<LinkedList<Integer>> listOfDepth(Node tree) {
		Queue<Node> que = new LinkedList<Node>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

		que.add(tree);
		processQue(que, list);

		int levels = (int) (Math.log(list.size() + 1) / Math.log(2));
		for (int i = 0; i < levels; i++) {
			int startIndex = (int) (Math.pow(2, i) - 1);
			int offset = (int) (Math.pow(2, i));
			result.add(new LinkedList<Integer>());
			for (int j = startIndex; j < startIndex + offset; j++) {
				if (list.get(j) != null) {
					result.get(i).add(list.get(j));
				}
			}
		}
		return result;
	}

	public static void processQue(Queue<Node> que, ArrayList<Integer> list) {
		while (!que.isEmpty()) {
			Node element = que.poll();
			if (element != null) {
				que.add(element.leftNode);
				que.add(element.rightNode);
				list.add(element.data);
			} else {
				list.add(null);
			}
		}
	}

	public Node getRoot() {
		return root;
	}

	public static void main(String[] args) {
		ListOfDepth test = new ListOfDepth();
		ArrayList<LinkedList<Integer>> result = ListOfDepth.listOfDepth(test.getRoot());
		System.out.println(result);
	}
}
