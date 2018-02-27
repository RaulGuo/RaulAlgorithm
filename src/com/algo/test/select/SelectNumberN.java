package com.algo.test.select;

/**
 * 选择问题。也就是在n个元素的集合中寻找第k小的元素
 * 的问题。
 * @author guozhen@proudsmart.com
 *
 */
public class SelectNumberN {

	/**
	 * lumoto划分算法的实现：
	 * 对于任何一个子数组arr[l....r](0 <= l <= r <= arr.length-1
	 * 该数组由连续三段组成，这三段按顺序排在中轴P的后边：一个段为已知比P小的元素，一段为已知比P大的元素，
	 * 还有一段未同P比较的元素。
	 * 
	 * TODO 代码有问题
	 * 
	 * @param nums
	 * @param start
	 * @param end
	 * @return
	 */
	public static int lumotoMedian(int[] nums, int start, int end){
		for(int i = 0; i < nums.length; i++){
			System.out.print(nums[i]+", ");
		}
		System.out.println();
		int begin= nums[start];
		int result = start;
		for(int i = start+1; i<= end; i++){
			if(nums[i] < begin){
				result = result+1;
				swap(nums, result, i);
			}
		}
		
		swap(nums, start, result);
		return result;
	}
	
	private static void swap(int[] nums, int m, int n){
		int tmp = nums[m];
		nums[m] = nums[n];
		nums[n] = tmp;
		for(int i = 0; i < nums.length; i++){
			System.out.print(nums[i]+", ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] nums = {4,1,10,8,7,12,9,2,15};
		int result = lumotoMedian(nums, 0, nums.length-1);
		System.out.println("result of median: "+result);
	}
}
