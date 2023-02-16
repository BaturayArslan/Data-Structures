package com.mycompany.datastructures;

public class TreeNode<K extends Integer> {
	private K data;
	private TreeNode<K> left;
	private TreeNode<K> right;

	public TreeNode(K value, TreeNode left, TreeNode right) {
		this.data = value;
		this.left = left;
		this.right = right;
	}

	public TreeNode(K value) {
		this.data = value;
	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}

	public K getData() {
		return data;
	}

	public void setData(K value) {
		this.data = value;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

}
