package com.algo.test.hard;

/**
 * 分享糖果问题。
 * 有一排小孩排队站着等着分糖果，每个小孩都有一个评分，
 * 现在要求：每个小孩至少要分到一个糖果，且如果小孩比临近的小孩评分高，则他要比相邻的两个小孩
 * 得到更多糖果。
 * 求问：我们至少需要准备多少糖果。
 * 
 * 例如：
 * 给定评级1,2,2 则至少要4个糖果（1,2,1）
 * 如果评级是1,3,4，则至少要6个糖果(1,2,3)
 * 
 * http://www.lintcode.com/zh-cn/problem/candy/
 * @author guozhen@proudsmart.com
 */
public class ShareCandyProblem {
	
	/**
	 * 使用回溯法求最少糖果数量。
	 * 初始化每个人都给一个糖果。
	 * 第一个小孩如果评分大于第二个，要么给两个糖果。
	 * 然后从第二个开始回溯。假设回溯是，index为i
	 * 如果第i个小孩评分大于其前一个，而糖果小于等于其前一个，则将其糖果数（用minCandies[i]表示）设置为minCandies[i-1]+1，然后继续向前循环。
	 * 如果第i个小孩评分小于其前一个，而其糖果数量大于等于前一个，则将前一个小孩的糖果数设置为minCandies[i]+1，然后向后回溯。
	 * 
	 * 这样，回溯到最后，就是每个位置的最少糖果数量。
	 * @return
	 */
	public static int minCandyNumsByRecall(int[] points){
		int[] minCandies = new int[points.length];

		for(int i = 0; i < minCandies.length; i++){
			minCandies[i] = 1;
		}
		
		if(points[0] > points[1]){
			minCandies[0] = 2;
		}else{
			minCandies[0] = 1;
		}
		
		int index = 1;
		while(index < points.length){
			if(points[index] > points[index-1]){
				if(minCandies[index] <= minCandies[index-1]){	
					minCandies[index] = minCandies[index-1]+1;
				}
				index++;
			}else if(points[index] < points[index-1]){
				if(minCandies[index] >= minCandies[index-1]){
					minCandies[index-1] = minCandies[index]+1;
					index--;
					continue;
				}else{
					index++;
				}
			}
			
		}
		
		int sum = 0;
		for(int i = 0; i < minCandies.length; i++){
			System.out.print(minCandies[i]+", ");
			sum += minCandies[i];
		}
		System.out.println();
		System.out.println("min candies : "+sum);
		
		return sum;
	}
	
	public static void main(String[] args) {
		int[] points = {1,2,4,3,2,1};
		//1,2,4,3,2,1
		minCandyNumsByRecall(points);
	}
//	public static int minCandyNums(int[] points){
//		int[] minCandies = new int[points.length];
//		if(points[0] > points[1]){
//			minCandies[0] = 2;
//		}else{
//			minCandies[0] = 1;
//		}
//		
//		if(points[points.length-1] > points[points.length-2]){
//			minCandies[points.length-1] = 2;
//		}else{
//			minCandies[points.length-1] = 1;
//		}
//		
//		for(int i = 1; i < points.length-1; i++){
//			if(points[i] > points[i-1] && points[])
//		}
//	}
}
