package algorithm_examples;

/* 
	Minimal Tree: Given a sorted (increasing order) array with unique integer elements,
	write an algorithm to create a binary search tree with minimal height.
 */

public class MinimalTree {
	private static class Node {
		private int data;
		private Node leftNode;
		private Node rightNode;

		public Node(int data) {
			this.data = data;
		}

		public void inorderTraversal(Node root) {
			if (root == null) {
				return;
			}

			inorderTraversal(root.leftNode);
			System.out.printf("%d ", root.data);
			inorderTraversal(root.rightNode);

		}

	}

	public static Node minimalTree(int[] list) {
		return constructTree(list, 0, list.length - 1);
	}

	private static Node constructTree(int[] list, int begin, int end) {
		if (end - begin == 1) {
			Node greaterNode = new Node(list[end]);
			Node smallerNode = new Node(list[begin]);
			greaterNode.leftNode = smallerNode;
			return greaterNode;
		} else if (end - begin == 0) {
			Node newNode = new Node(list[end]);
			return newNode;
		}

		int middleIndex = ((end - begin) / 2) + begin;
		Node newNode = new Node(list[middleIndex]);
		newNode.leftNode = constructTree(list, begin, middleIndex - 1);
		newNode.rightNode = constructTree(list, middleIndex + 1, end);

		return newNode;

	}

	public static void main(String[] args) {
		int[] list = { 5, 6, 8, 12, 17, 19, 20, 25 };
		Node root = MinimalTree.minimalTree(list);
		root.inorderTraversal(root);
	}
}
