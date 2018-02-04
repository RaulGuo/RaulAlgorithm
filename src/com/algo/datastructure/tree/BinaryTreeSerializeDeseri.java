package com.algo.datastructure.tree;

/**
 * 设计一个算法，用来序列化和反序列化一个二叉树。
 * 将树写入一个文件称为序列化，读取文件后需要重建该树，称为反序列化。
 * 
 * 注意：如何反序列化或序列化二叉树是没有限制的，
 * 你只需要确保可以将二叉树序列化为一个字符串，
 * 并且可以将字符串反序列化为原来的树结构。
 * 
 * Definition of TreeNode:
 * 
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 *
 * @author guozhen@proudsmart.com
 *
 */

public class BinaryTreeSerializeDeseri {
	
	/**
	 * 给定一个root TreeNode，将其序列化成一个字符串。
	 * 序列化方式：从root开始，节点之间的值以","分隔。
	 * 在字符串中的顺序为：root, left, right。
	 * 序列化的访问顺序是BFS，也就是广度遍历优先。
	 * 如果一个节点的某个子节点为空，则用#来标识。
	 * 
	 * @param root
	 * @return
	 */
	public static String serialize(TreeNode root){
		StringBuilder sb = new StringBuilder();
		sb.append(root.val+",");
		
		append(sb, root);
		
		return sb.toString();
	}
	
	public static void append(StringBuilder sb, TreeNode node){
		sb.append(node.left == null ? "#," : node.left.val+",");
		sb.append(node.right == null ? "#," : node.right.val+",");
		
		if(node.left != null){
			append(sb, node.left);
		}
		
		if(node.right != null){
			append(sb, node.right);
		}
		
	}
	
	public static TreeNode deserialize(String data){
		String[] nodes = data.split(",");
		for(int i = 0; i < nodes.length; i++){
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);//A
		root.setLeft(3);//C
		root.setRight(6);//F
		root.left.setLeft(2);//B
		root.right.setLeft(7);
		root.right.left.setLeft(5);
		
		System.out.println(serialize(root));
	}


}
