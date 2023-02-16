package algorithm_examples;

/*
	Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
	algorithm to determine if T2 is a subtree of Tl.
	A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of n is identical to T2.
	That is, if you cut off the tree at node n, the two trees would be identical. 
 */

public class CheckSubtree {
	private static class Node {
		private int data;
		private Node leftNode;
		private Node rightNode;

		public Node(int data) {
			this.data = data;
		}
	}

	public static boolean isSubtree(Node T1, Node T2) {
		if (T1 == null || T2 == null) {
			return false;
		}

		if (T1 == T2) {
			if (isEqualTree(T1, T2)) {
				return true;
			}
		}

		return isSubtree(T1.leftNode, T2) || isSubtree(T1.rightNode, T2);
	}

	private static boolean isEqualTree(Node T1, Node T2) {
		if (T1 == null && T2 == null) {
			return true;
		}
		if (T1 != T2) {
			return false;
		}
		return isEqualTree(T1.leftNode, T2.leftNode) && isEqualTree(T1.rightNode, T2.rightNode);
	}

	public static void main(String[] args) {
	}
}
