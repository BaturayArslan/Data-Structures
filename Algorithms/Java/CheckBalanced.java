package algorithm_examples;

/*
	Implement a function to check if a binary tree is balanced. For the purposes of
	this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
	node never differ by more than one
 */

public class CheckBalanced {
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

	private static class Status {
		private boolean result;
		private int height;

		public Status(int height) {
			this.result = true;
			this.height = height;
		}

		public Status(boolean result) {
			this.result = result;
			this.height = 0;
		}
	}

	public static boolean isBalanced(Node root) {
		Status status = recursive_check(root);
		return status.result;

	}

	private static Status recursive_check(Node root) {
		if (root == null) {
			return new Status(-1);
		}
		Status leftStatus = recursive_check(root.leftNode);
		if (!leftStatus.result) {
			return leftStatus;
		}
		Status rightStatus = recursive_check(root.rightNode);
		if (!leftStatus.result) {
			return rightStatus;
		}
		if (Math.abs(leftStatus.height - rightStatus.height) > 1) {
			return new Status(false);
		}

		Status status = new Status(Math.max(leftStatus.height, rightStatus.height) + 1);
		return status;

	}

	public static void main(String[] args) {
		Node root = new Node(10);
		root.add(20, root);
		root.add(5, root);
		root.add(7, root);
		root.add(8, root);
		root.add(2, root);
		root.add(25, root);
		System.out.println(isBalanced(root));

	}
}
