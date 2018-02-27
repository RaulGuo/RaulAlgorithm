package com.algo.datastructure.tree;

public class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val){
		this.val = val;
		this.left = this.right = null;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	public TreeNode setLeft(int left) {
		this.left = new TreeNode(left);
		return this.left;
	}
	
	public TreeNode setRight(int right) {
		this.right = new TreeNode(right);
		return this.right;
	}

}
