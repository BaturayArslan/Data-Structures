package com.mycompany.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/* Binary Search Tree Implementation */

interface Tree<K extends Integer> {
	void insert(K item);

	// void delete(K item);

	public int height(TreeNode root);

	TreeNode<K> search(K item);

	ArrayList<K> levelTraversal();

	ArrayList<K> inorderTraversal();

	// K[] PostorderTraversal();
}

public class BST<K extends Integer> implements Tree<K> {
	private TreeNode<K> root;

	public void insert(K item) {
		if (root == null) {
			root = new TreeNode(item);
			return;
		}

		insertItem(item, root);

	}

	private void insertItem(K item, TreeNode root) {
		// check item if its belong to right or left subtree
		if (root.getData() >= item) {
			if (root.getLeft() == null) {
				// insert element
				root.setLeft(new TreeNode<K>(item));
			} else {
				// dive deeper
				insertItem(item, root.getLeft());
			}
		} else {
			if (root.getRight() == null) {
				// insert element
				root.setRight(new TreeNode<K>(item));
			} else {
				// dive deeper
				insertItem(item, root.getRight());
			}
		}

	}

	public TreeNode<K> search(K item) {
		return searchItem(item, root);
	}

	private TreeNode<K> searchItem(K item, TreeNode<K> node) {
		if (root == null) {
			return null;
		}

		if (root.getData() == item) {
			return root;
		}

		if (root.getData() >= item) {
			return searchItem(item, root.getLeft());
		} else {
			return searchItem(item, root.getRight());
		}
	}

	public ArrayList<K> levelTraversal() {
		ArrayList<K> list = new ArrayList<K>();
		Queue<TreeNode<K>> que = new LinkedList<TreeNode<K>>();
		que.add(root);
		return processQue(que, list);
	}

	private ArrayList<K> processQue(Queue<TreeNode<K>> que, ArrayList<K> list) {
		TreeNode<K> element;
		while (!que.isEmpty()) {
			element = que.remove();
			if (element == null) {
				continue;
			}
			list.add(element.getData());
			que.add(element.getLeft());
			que.add(element.getRight());
		}
		return list;

	}

	public TreeNode getRoot() {
		return root;
	}

	public ArrayList<K> inorderTraversal() {
		ArrayList<K> list = new ArrayList();
		inorderTraverse(root, list);
		return list;
	}

	private void inorderTraverse(TreeNode<K> root, ArrayList<K> list) {
		if (root == null) {
			return;
		}
		inorderTraverse(root.getLeft(), list);
		list.add(root.getData());
		inorderTraverse(root.getRight(), list);

	}

	public int height(TreeNode root) {
		if (root == null) {
			return -1;
		}
		int leftHeight = height(root.getLeft());
		int rightHeight = height(root.getRight());
		return Integer.max(leftHeight, rightHeight) + 1;
	}

	public static void main(String[] args) {
		BST test = new BST();
		test.insert(10);
		test.insert(5);
		test.insert(15);
		test.insert(2);
		test.insert(8);
		test.insert(1);
		test.insert(3);
		test.insert(9);
		test.insert(15);

		int height = test.height(test.getRoot());
		System.out.println(height);

		ArrayList<Integer> list = test.levelTraversal();
		list = test.levelTraversal();
		System.out.println(list);

		list = test.inorderTraversal();
		System.out.println(list);

	}
}
