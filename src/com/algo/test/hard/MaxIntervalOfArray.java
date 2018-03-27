package com.algo.test.hard;

/**
 * 给定一个未经排序的数组，请找出其排序表中连续两个要素的最大间距。
 * 如果数组中的要素少于 2 个，请返回 0
 * 
 * 例如：
 * 给定数组 [1, 9, 2, 5]，其排序表为 [1, 2, 5, 9]，其最大的间距是在 5 和 9 之间，= 4
 * 
 *  [-5, 1, 9, 5, 2]
 *  -5 1 6
 *  1 9 8
 *  1 5 4
 * 该方法可以通过先排序，再求最大间距的方法来解决，但排序时间复杂度为O(n*log(n))
 * 是否可以用线性的时间和空间复杂度来解决这个问题？
 * 
 * 
 * @author guozhen@proudsmart.com
 *
 */
public class MaxIntervalOfArray {
	
	public static int getMaxInterval(int[] nums){
		int min = Math.min(nums[0], nums[1]), max = Math.max(nums[0], nums[1]), interval = Math.abs(nums[0]-nums[1]);
		
		for(int i = 2; i < nums.length; i++){
			//
			if(nums[i] < min){
				
			}else if(nums[i] < max){
				
			}else{
				
			}
		}
		
		return interval;
	}

	
	
	
}
