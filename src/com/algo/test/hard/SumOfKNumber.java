package com.algo.test.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 给定n个不同的整数（数组），一个整数k以及一个target（整数），
 * 求问在这n个数中找出k个整数，使得其和为target的方案有多少。
 * http://www.lintcode.com/zh-cn/problem/k-sum/
 * @author guozhen@proudsmart.com
 *
 */
public class SumOfKNumber {
	
	
	
	/**
	 * 采用动态规划的方式来计算K数和的问题。
	 * 思路：定义动态规划方程：dp(i,k,target)表示从前i个数字中找到k个数，使其和等于target的方案数。
	 * 此时，转移方程为：
	 * dp(i,k,target) = dp(i-1,k,target)+dp(i-1, k-1, target-nums[i])
	 * 代表的含义是：从前i个数中找到k个数，使其和等于target的方案数的值等于：
	 * 从前i-1个数中找到k个数，使其和等于target的数量加上从前i-1个数中找到k-1个数，
	 * 使其值等于target减掉第i个数的值的数量。
	 * 
	 * @param nums
	 * @param k
	 * @param target
	 * @return
	 */
	public static int countPossibleDP(int[] nums, int k, int target){
		int len = nums.length;
		
		int[][][] dp = new int[len+1][k+1][target+1];
		//对dp进行初始化，也就是只选择一个元素，其数值等于nums中不同的值的情况的数量都是1。
		for(int i = 0; i < len; i++){
			if(nums[i] <= target){//如果nums中的某个值自己就大于target，则该值没有参与计算的必要。（没有负数）
				for(int j = i+1; j <= len; j++){
					dp[j][1][nums[i]] = 1;
				}
			}
		}
		
		
		//分别计算取两个元素，三个元素，直到取k个元素。
		for(int i = 1; i <= len; i++){
			for(int j = 2; j <= k && j <= i; j++){
				for(int s = i; s <= target; s++){
					dp[i][j][s] = dp[i-1][j][s] + (s>nums[i-1] ? dp[i-1][j-1][s-nums[i-1]] : 0);
				}
			}
		}
		
		return dp[len][k][target];
	}
	
	public static int countPossibleWithNegativeDP(int[] nums, int k, int target){
		//dp[a][b][c]代表从nums中的前a个数中取出b个数，其值的和为c的可能性的数量。
		int[][][] dp = new int[nums.length+1][k+1][target+1];
		
		//从前a个数中取出1个数，其值为其值可能是nums[0]到nums[a]的任何一个数的可能性都是1。
		for(int i = 0; i < nums.length; i++){
			for(int j = i+1; j <= nums.length; j++){
				dp[j+1][1][nums[i]] = 1;
			}
		}
		
		//我们定义的状态转移方程为: dp[a][b][c] = dp[a-1][b-1][target-nums[a]]+ dp[a][b-1][target]
		//已经初始化了取一个元素时的所有可能的情形。以此为基础，计算所有的可能性
		for(int i = 1 ; i <= nums.length; i++){
			for(int j = 2; j <=k && j <= i; j++){
				for(int s = i; s <= target; s++){
					dp[i][j][s] = dp[i-1][j][s] + (s > nums[i-1] ? dp[i-1][j-1][s-nums[i-1]] : 0);
				}
			}
		}
		
		return dp[nums.length][k][target];
	}
	
	
	public static int countPossibleDP2D(int nums[], int k, int target){
		//该二维数组dp[a][b]表示，取a个数，和为b的可能选项的数目。
		//定义dp[0][0]=1代表去0个数且和为0的可能选项数量为1
		int[][] dp = new int[k+1][target+1];
		dp[0][0] = 1;
		for(int i = 1; i <= nums.length; i++){
			for(int j = k; j >= 1; j--){
				for(int s = target; s >= nums[i-1]; s--){
					dp[j][s] += dp[j-1][s-nums[i-1]];
				}
				print(dp);
			}
		}
		
		return dp[k][target];
	}
	
	
	public static void print(int[][] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[0].length;j++){
				System.out.print(arr[i][j]+",");
			}
			System.out.println();
		}
		System.out.println("---------------------");
	}
	/**
	 * 思路：
	 * 该问题可以转化为遍历所有的从nums中选出k个元素的可能性的集合。
	 * 获取所有可能的组合可以使用递归的方式来实现。
	 * 
	 * 	
	 * @param nums
	 * @param k
	 * @param target
	 * @return
	 */
	public static int countPossible(int[] nums, int k, int target){
		int count = 0;
		
		for(int firstIndex = 0; firstIndex < nums.length - k; firstIndex++){
			int lastIndex = firstIndex + k - 1;
		}
		
		return count;
	}
	
	private static int getSumOfTopK(int[] arr, int k, int startIndex){
		int sum = 0;
		for(int i = startIndex; i < startIndex+k ; i++){
			sum = sum+arr[i];
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5,6,7};
		int target = 7;
		int k = 2;
		int count = countPossible(nums, k, target);
		System.out.println(count);
	}
}
