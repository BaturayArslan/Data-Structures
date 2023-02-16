package algorithm_examples;

/*
	BST Sequences: A binary search tree was created by traversing through an array from left to right
	and inserting each element. Given a binary search tree with distinct elements, print all possible
	arrays that could have led to this tree. 
 */

import java.util.*;

public class BstSequence {

	private static class Node {
		private int data;
		private Node leftNode;
		private Node rightNode;

		public Node(int data) {
			this.data = data;
		}
	}

	private static class Solution1 {
		// My level based solution that does breath first search first then get lists of
		// all element that in same level then calculate permutation of all level lists.
		public static void bstSequence(Node root) {
			int height = getHeight(root);
			Integer[] list = bfs(root, height);
			HashMap<Integer, ArrayList<Integer>> map = getLevels(list, height);

		}

		public static Integer[] bfs(Node root, int height) {
			if (root == null) {
				return null;
			}

			Queue<Node> que = new LinkedList<Node>();
			Integer[] list = new Integer[(int) (Math.pow(2, height + 1) - 1)];

			que.add(root);
			bfs(que, list);
			return list;

		}

		private static void bfs(Queue<Node> que, Integer[] list) {
			int index = 0;
			while (index < list.length) {
				Node node = que.remove();
				if (node != null) {
					que.add(node.leftNode);
					que.add(node.rightNode);
					list[index] = node.data;
				} else {
					que.add(null);
					que.add(null);
					list[index] = null;
				}
				index++;
			}
		}

		private static HashMap<Integer, ArrayList<Integer>> getLevels(Integer[] list, int height) {
			HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
			int level = 0;
			while (level <= height) {
				int minIndex = (int) (Math.pow(2, level) - 1);
				int maxIndex = (int) (Math.pow(2, level + 1) - 1);
				ArrayList<Integer> resultList = new ArrayList<Integer>();

				for (int i = minIndex; i < maxIndex; i++) {
					if (list[i] != null)
						resultList.add(list[i]);
				}

				map.put(level, resultList);
				level++;
			}

			return map;
		}

		public static int getHeight(Node root) {
			if (root == null) {
				return -1;
			}

			return Math.max(getHeight(root.leftNode), getHeight(root.rightNode)) + 1;

		}
	}

	private static class Solution2 {
		// Recursive Solution
		public static ArrayList<LinkedList<Integer>> binarySequence(Node root) {
			ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
			if (root == null) {
				LinkedList<Integer> list = new LinkedList<Integer>();
				result.add(list);
				return result;
			}
			ArrayList<LinkedList<Integer>> leftSequence = binarySequence(root.leftNode);
			ArrayList<LinkedList<Integer>> rightSequence = binarySequence(root.rightNode);

			for (LinkedList<Integer> leftList : leftSequence) {
				for (LinkedList<Integer> rightList : rightSequence) {
					for (LinkedList<Integer> mixList : mixLists(leftList, rightList, new LinkedList<Integer>())) {
						mixList.addFirst(root.data);
						result.add(mixList);
					}
				}
			}

			// for (LinkedList<Integer> list : result) {
			// list.addFirst(root.data);
			// }

			return result;
		}

		public static ArrayList<LinkedList<Integer>> mixLists(LinkedList<Integer> firstList,
				LinkedList<Integer> secondList, LinkedList<Integer> prefixList) {
			ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
			if (firstList.isEmpty()) {
				result = mergeLists(prefixList, secondList);
				return result;
			} else if (secondList.isEmpty()) {
				result = mergeLists(prefixList, firstList);
				return result;
			}

			LinkedList<Integer> firstClone = (LinkedList<Integer>) firstList.clone();
			LinkedList<Integer> secondClone = (LinkedList<Integer>) secondList.clone();
			LinkedList<Integer> prefixClone = (LinkedList<Integer>) prefixList.clone();
			Integer leftHead = firstClone.removeFirst();
			prefixClone.add(leftHead);

			ArrayList<LinkedList<Integer>> leftResult = mixLists(firstClone, secondClone, prefixClone);

			Integer rightHead = secondClone.removeFirst();
			firstClone.addFirst(prefixClone.removeLast());
			prefixClone.add(rightHead);

			ArrayList<LinkedList<Integer>> rightResult = mixLists(firstClone, secondClone, prefixClone);

			leftResult.addAll(rightResult);
			return leftResult;

		}

		public static ArrayList<LinkedList<Integer>> mergeLists(LinkedList<Integer> firstList,
				LinkedList<Integer> secondList) {
			ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
			LinkedList<Integer> mergedList = new LinkedList<Integer>();
			for (Integer data : firstList) {
				mergedList.add(data);
			}
			for (Integer data : secondList) {
				mergedList.add(data);
			}
			result.add(mergedList);
			return result;
		}
	}

	public static void main(String[] args) {
		Node root = new Node(5);
		root.leftNode = new Node(2);
		root.rightNode = new Node(6);
		root.leftNode.leftNode = new Node(1);
		root.leftNode.rightNode = new Node(3);
		root.rightNode.rightNode = new Node(7);
		ArrayList<LinkedList<Integer>> result = Solution2.binarySequence(root);
		System.out.println(result);
	}
}
