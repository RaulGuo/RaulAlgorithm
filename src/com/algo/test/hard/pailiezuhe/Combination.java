package com.algo.test.hard.pailiezuhe;

import java.util.ArrayList;
import java.util.List;

/**
 * 排列算法的实现。主要是通过递归的方式来实现获取从m个元素中取n个元素的所有可能的组合。
 * @author guozhen@proudsmart.com
 *
 */
public class Combination {
	
	/**
	 * 采用递归的方式获取所有的组合的可能性。
	 * 方法：
	 * 当递归到最后一个元素时，去打印出所有的可能性。
	 * 否则：从
	 * @param s
	 * @param nums
	 * @param n
	 */
	public static void printAllCombination(String s, int[] nums, int n){
		if(n == 1){
			//如果只剩下一个元素可选则将每个元素都添加到字符串上即可。
			for(int i = 0; i < nums.length; i++){
				System.out.println(s+nums[i]);
			}
		}else{
			for(int i = 0; i < nums.length - n +1; i++){
				String ss = s + nums[i]+", ";
				//建立子数组，将nums的前i个元素截掉。
				int[] childNums = new int[nums.length - i -1];
				for(int j = 0; j < nums.length -i -1; j++){
					childNums[j] = nums[i+j+1];
				}
				
				printAllCombination(ss, childNums, n-1);
			}
		}
	}
	
	public static void printAllCombinationNew(String s, int[] nums, int startIndex, int n){
		if(n == 1){
			//如果只剩下一个元素可选则将每个元素都添加到字符串上即可。
			for(int i = startIndex; i < nums.length; i++){
				System.out.println(s+nums[i]);
			}
		}else{
			for(int i = startIndex; i < nums.length - n +1; i++){
				String ss = s + nums[i]+", ";
//				//建立子数组，将nums的前i个元素截掉。
//				int[] childNums = new int[nums.length - i -1];
//				for(int j = 0; j < nums.length -i -1; j++){
//					childNums[j] = nums[i+j+1];
//				}
				printAllCombinationNew(ss, nums, i+1, n-1);
			}
		}
	}
	
	/**
	 * 通过递归的方式来获取从nums中获取n的所有可能的组合。
	 * @param nums
	 * @param n
	 * @return
	 */
	public static List<int[]> getAllCombinationsRecursion(int[] nums, int n){
		int currArray[] = new int[n];
		List<int[]> list = new ArrayList<int[]>();
		
		findCombinationsOfChildNums(nums, 0, n, list, currArray);
		
		return list;
	}
	
	/**
	 * 从nums中，firstIndex开始之后的元素中，挑出n个元素，添加到currArray的后n个元素中。
	 * @param nums
	 * @param firstIndex
	 * @param n
	 * @param result
	 * @param currArray
	 */
	private static void findCombinationsOfChildNums(int[] nums, int firstIndex, int n, List<int[]> result, int[] currArray){
		if(n == 1){
			//取最后一个元素，设置为currArray的最后一个元素，并放入result中
			for(int i = firstIndex; i< nums.length; i++){
				currArray[currArray.length-1] = nums[i];
				result.add(currArray.clone());
			}
		}else{
			//如果要选n个元素，则最多选到倒数第n个元素。否则剩下的元素不够选。
			for(int i = firstIndex; i < nums.length-n+1; i++){
				currArray[currArray.length-n] = nums[i];
				findCombinationsOfChildNums(nums, i+1, n-1, result, currArray);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5,6};
		printAllCombinationNew("", nums, 0, 2);
		List<int[]> result = getAllCombinationsRecursion(nums, 4);
		result.forEach(x -> {
			for(int i = 0; i < x.length; i++){
				System.out.print(x[i]+",");
			}
			System.out.println("---------------");
		});
	}
}
