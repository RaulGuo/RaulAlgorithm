package com.algo.test.hard;

/**
 * 木材加工问题。有一些原木，现在要把这些木头切成一些长度相同的小段木头，
 * 得到的小段木头的数量至少为k。我们希望得到的小段越长越好，求小段木头的最大长度。
 * 
 * 例如：有以下三根木头，长度分别是232, 124, 456, 最少数目为7。
 * 此时，我们可以得到的最大的长度是114。
 * 
 * @author guozhen@proudsmart.com
 *
 */
public class WoodProcessProblem {
	
	/**
	 * 先找到最长的木头，将最长的木头切成minNum段，此时每段木头长度为l1。
	 * 
	 * 然后逐个尝试将最长的木头切换成minNum--段。此时，每段木头的长度必然大于前一轮的长度。
	 * 在循环之中，要检测剩下的几根木头是否能够按照当前的长度切成剩余的木头。
	 * 
	 * 如果剩余的木头足够且出剩余的木头，就继续循环。如果不够，就返回上一轮的长度。
	 * 
	 * @param woods
	 * @param maxNum
	 * @return
	 */
	public static int getMaxWoodLength(int[] woods, int minNum){
		int maxIndex = maxIndex(woods);
		int maxValue = woods[maxIndex];
		int splitLongestWoodLength = maxValue/minNum;
		
		//longestWoodSplit代表最长的木头要切成多少段
		for(int longestWoodSplit = minNum-1; longestWoodSplit >= 1; longestWoodSplit--){
			int currLongestWoodSplitLength = maxValue/longestWoodSplit;
			int woodNumToSplitForLeft = minNum-longestWoodSplit;
			if(!canLeftWoodSplitMaxLengthToCount(woods, maxIndex, currLongestWoodSplitLength, woodNumToSplitForLeft)){
				return splitLongestWoodLength;
			}else{
				splitLongestWoodLength = currLongestWoodSplitLength;
			}
		}
		
		return splitLongestWoodLength;
	}
	
	private static boolean canLeftWoodSplitMaxLengthToCount(int[] woods, int maxIndex, int currLength, int count){
		int countSum = 0;
		for(int i = 0; i < woods.length; i++){
			if(i != maxIndex){
				countSum += woods[i]/currLength;
			}
		}
		
		if(countSum >= count)
			return true;
		else
			return false;
	}
	
	public static int maxIndex(int[] woods){
		int maxIndex = 0;
		int maxValue = woods[0];
		for(int i = 1; i < woods.length; i++){
			if(maxValue < woods[i]){
				maxIndex = i;
				maxValue = woods[i];
			}
		}
		
		return maxIndex;
	}
	
	public static void main(String[] args) {
		int[] woods = {232, 124, 456};
		int maxLength = getMaxWoodLength(woods, 7);
		System.out.println(maxLength);
	}
}
