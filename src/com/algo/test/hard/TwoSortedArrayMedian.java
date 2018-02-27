package com.algo.test.hard;
/**
 * 给定两个排序数组。获取这两个排序数组放在一起的中位数。
 * http://www.lintcode.com/zh-cn/problem/median-of-two-sorted-arrays/
 * @author guozhen@proudsmart.com
 *
 */
public class TwoSortedArrayMedian {

	
	private static Integer getMeidanOfSortedArray(int[] a, int[] b){
		int indexA = 0;
		int indexB = 0;
		int medianIndex = (a.length+b.length)/2;
		for(int i = 0; i < medianIndex; i++){
				
			if(a[indexA] > b[indexB]){
				if(i == medianIndex-1){
					return b[indexB];
				}else{
					indexB++;
				}
			}else{
				if(i == medianIndex-1){
					return a[indexA];
				}else
					indexA++;
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		int[] a = new int[]{1,2,3};
		int[] b = new int[]{3,4,5,8};
		Integer i = getMeidanOfSortedArray(a, b);
		System.out.println("median number is:"+i);
	}
}
