package com.algo.test.hard.pailiezuhe;

public class CombinationNew {
	
	public static void printAllCombinations(int[] nums, int n){
		printCombination("", 0, nums, n);
	}
	
	private static void printCombination(String str, int startIndex, int[] nums, int n) {
		if(n == 1){
			for(int i = startIndex; i < nums.length; i++){
				System.out.println(str+", "+nums[i]);
			}
		}else{
			for(int i = startIndex; i < nums.length - n +1; i++){
				String currIndexStr = str+", "+nums[i];
				printCombination(currIndexStr, i+1, nums, n-1);
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5,6};
		printAllCombinations(nums, 3);
	}
	
}
