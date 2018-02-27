package com.algo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数类型的数组，数组中可能包含重复元素。
 * 要求返回该数组的所有可能的子集。
 * 
 * 实现方法一：使用回溯算法来实现。
 * 
 * 
 * @author guozhen@proudsmart.com
 *
 */
public class SubsetsWithDuplicate {


	
	/**
	 * 获取nums的index下标之后(包含下标)的所有的值。
	 * 例如，nums为[0,1,2], index为0，则返回结果为[0,1,2]
	 * @param nums
	 * @param index
	 * @return
	 */
	private static List<Node> getPossibleValues(int[] nums, Node node){
		int index = node.index;
		List<Node> result = new ArrayList<Node>();
		for(int i = 0; i < nums.length-index; i++){
			Node n = new Node(nums[index+i], index+1);
			n.setParent(node);
			result.add(n);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
//		int[] result = getPossibleValues(new int[]{0,1,3}, 0);
//		for(int i = 0; i < result.length; i++){
//			System.out.println(result[i]);
//		}
		
		
	}
	
	
	public static class Node{
		Integer val;
		Integer index;
		Node parent;
		
		public Node(Integer val, Integer index) {
			this.val = val;
			this.index = index;
		}

		public Node addChild(Integer t, Integer index){
			Node node = new Node(t, index);
			node.parent = this;
			return node;
		}

		public Integer getVal() {
			return val;
		}

		public void setVal(Integer val) {
			this.val = val;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}
		
	}
}
