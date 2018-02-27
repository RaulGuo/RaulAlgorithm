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
	
	/**
	 * 拼接某个节点的左子节点和右子节点到StringBuilder中，并递归拼接左子节点的子节点和右子节点的子节点。
	 * 若节点为空，拼接个#（代表元素为空）到字符串中。
	 * 
	 * @param sb
	 * @param node
	 */
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
	
	/**
	 * 反序列化。
	 * 
	 * 以main方法中的树为例：
	 * 1 -> 3, 6
	 * 3 -> 2, 9
	 * 2 -> #, #
	 * 9 -> #, #
	 * 6 -> 7, #
	 * 7 -> 5, #
	 * 5 -> #, #
	 * 
	 * 该树序列化后，结果是：
	 * 1,3,6,2,9,#,#,#,#,7,#,5,#,#,#,
	 * 要对此树进行反序列化，思路是：
	 * root是第一个元素，其左右子节点的访问顺序为2,3.
	 * 此时，第二个元素的左右子节点的访问顺序是4,5
	 * 然后4的左右子节点的访问顺序是6,7
	 * 这是一个比较明显的递归+对数组的顺序访问。
	 * 
	 * 以上思路的实现算法：
	 * 1. 将字符串先按照分隔符“,”进行split，获得一个字符串数组。先获取数组第一个元素，
	 * 初始化root节点。
	 * 2. 递归调用左右节点的添加程序。
	 * 
	 * 
	 * @param data
	 * @return
	 */
	public static TreeNode deserialize(String data){
		String[] nodes = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
		appendFromRoot(nodes, root, 1);
		
		return root;
	}
	
	/**
	 * 需要递归调用的对数组进行反序列化的算法。
	 * 算法思路：
	 * 1. 如果数组的下一个元素是#, 则其左子节点为空，否则，设置左子节点。然后数组向下访问。
	 * 2. 若下一个元素是#，则右子节点为空；否则，设置右子节点。然后数组向下访问。
	 * 3. 若左子节点不是空，对左子节点递归调用。
	 * 4. 若右子节点不是空，对右子节点递归调用。
	 * @param nodes
	 * @param node
	 * @param index 这里选用AtomicInteger是为了将Integer在各个线程调用堆栈里，能继续将值传递下去。Integer理论上也可以，但由于这里对index的值做了修改，
	 * 所以在修改时，仍然会将其解包成基本类型。
	 * 
	 */
	private static int appendFromRoot(String[] nodes, TreeNode node, int index){
		TreeNode left = null, right = null;
		if(!"#".equals(nodes[index])){
			left = node.setLeft(Integer.parseInt(nodes[index]));
		}
		index++;
		
		if(!"#".equals(nodes[index])){
			right = node.setRight(Integer.parseInt(nodes[index]));
		}
		index++;
		
		if(left != null){
			index = appendFromRoot(nodes, left, index);
		}
		if(right != null){
			index = appendFromRoot(nodes, right, index);
		}
		
		return index;
	}
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);//A
		root.setLeft(3);//C
		root.setRight(6);//F
		root.left.setLeft(2);//B
		root.left.setRight(9);//B
		
		root.right.setLeft(7);
		root.right.left.setLeft(5);
		
		String ser = serialize(root);
		System.out.println(ser);
		
		TreeNode newRoot = deserialize(ser);
		System.out.println("deserialize result: \n"+serialize(newRoot));
	}


}
