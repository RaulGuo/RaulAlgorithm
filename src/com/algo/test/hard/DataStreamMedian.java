package com.algo.test.hard;

/**
 * http://www.lintcode.com/zh-cn/problem/data-stream-median/
 * 获取数据流的中位数。数据流用一个数组来表示。
 * 每次输入一个数，都对所收到的所有的数进行排序，并输出对应的中位数。
 * 中位数的计算规则是：(n-1)/2 n代表元素的数量（也就是数组的长度）
 * 例如：持续进入数组的数的列表为：[4, 5, 1, 3, 2, 6, 0]，则返回 [4, 4, 4, 3, 3, 3, 3]
 * @author guozhen@proudsmart.com
 *
 */

public class DataStreamMedian {
	
	/**
	 * nums的第index个数的数值为num。
	 * nums当前是排序的状态，需要将num插入到nums中的合适位置。
	 * 插入之后获取第(index-1)/2个元素
	 * @param nums
	 * @param index
	 * @param num
	 * @return
	 */
	public static void appendToSortedNum(int[] sortedNum, int index, int num){
		int indexToInsert = 0;
		for(int i = 0; i < index; i++){
			if(num <= sortedNum[0]){
				break;
			}else if(num >= sortedNum[index-1]){
				indexToInsert = index;
				break;
			}else if(num >= sortedNum[i] && (i+1 <= index || num <= sortedNum[i+1])){
				indexToInsert = i+1;
				break;
			}
		}
		
		for(int i = index; i > indexToInsert; i--){
			sortedNum[i] = sortedNum[i-1];
		}
		
		sortedNum[indexToInsert] = num;
	}
	
	public static int[] getMedianOfArray(int[] nums){
		int[] sortedNums = new int[nums.length];
		int[] medianNums = new int[nums.length];
		
		for(int index = 0; index < nums.length; index++){
			int num = nums[index];
			appendToSortedNum(sortedNums, index, num);
			medianNums[index] = sortedNums[index/2];
		}
		
		return medianNums;
	}
	
	
	
	public static void main(String[] args) {
		//，则返回 [4, 4, 4, 3, 3, 3, 3]
		int[] nums = new int[]{4, 5, 1, 3, 2, 6, 0};
		int[] result = getMedianOfArray(nums);
		for(int i = 0; i < result.length; i++){
			System.out.println(result[i]);
		}
	}
}
