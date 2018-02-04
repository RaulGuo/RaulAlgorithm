package com.algo.datastructure.tree;

/**
 * 对二叉树的广度优先遍历。
 * 广度优先遍历的算法逻辑：
 * 先访问root，然后访问左节点，然后访问右节点。
 * 在访问左节点的子节点，然后访问右节点的子节点。
 * 
 * @author guozhen@proudsmart.com
 *
 */

public class BFS {
	public static String bfs(TreeNode root){
		StringBuffer sb = new StringBuffer();
		sb.append(root.getVal());
		append(sb, root);
		return sb.toString();
	}
	
	public static void append(StringBuffer sb, TreeNode node){
		if(node.left != null)
			sb.append("," + node.left.val);
		if(node.right != null)
			sb.append("," + node.right.val);
		
		if(node.left != null){
			append(sb, node.left);
		}
		if(node.right != null){
			append(sb, node.right);
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);//A
		root.setLeft(3);//C
		root.setRight(6);//F
		root.left.setLeft(2);//B
		root.right.setLeft(7);
		root.right.left.setLeft(5);
		
		System.out.println(bfs(root));
	}
}
