package com.algo.test;

import java.util.HashMap;
import java.util.Map;

/**
 * 杆子分割问题：
 * http://www.lintcode.com/zh-cn/problem/cutting-a-rod/
 * 
 * 给一个 n 英寸长的杆子和一个包含所有小于等于 n 的尺寸的价格. 
 * 确定通过切割杆并销售碎片可获得的最大值.例如，如果棒的长度为8，
 * 并且不同长度部件的值如下，则最大可获得值为 22(通过切割两段长度 2 和 6 )
 * 长度    | 1   2   3   4   5   6   7   8  
 *  --------------------------------------------
 * 价格    | 1   5   8   9  10  17  17  20
 * 
 * 思路：从1到n逐个计算每个长度所可能出现最大价值，记录该价值。
 * 在更大的数计算长度时，通过将其分割成两个数，计算其可能的最大长度。
 * 
 * 例如：在这个例子中，2寸长的杆子，最大的价格计算方法为：5 > 1+1，所以取5。
 * 也就是如果有2寸的杆子，能获取的最大价值是5。而3寸的杆子，可能分成1+2，或者不分。
 * 则1+2能获取的最大值是1+5，小于3不分时的8。所以3对应的最大值为8，依次类推。
 * 
 * 这种思路是递推方法的思路，即有了小长度的杆的最大价值之后递推更长的杆的最大价值。
 * 
 * @author guozhen@proudsmart.com
 *
 */

public class CuttingRod {
	
	public static int cutting(int[] prices, int n) {
        //需要每一种单独的值所可能的最大值
        Map<Integer, Integer> maxPriceForIndex = new HashMap<Integer, Integer>();
        for(int i = 1 ; i <= n; i++){
            if(i == 1){
            	maxPriceForIndex.put(i, prices[i-1]);
            }else{
                int max = prices[i-1];
                for(int j = 1; j < i; j++){
                	int first = maxPriceForIndex.get(j);
                	int last = maxPriceForIndex.get(i-j);
                    if(max < first+last){
                        max = first+last;
                    }
                }
                maxPriceForIndex.put(i, max);
            }
        }
        
        return maxPriceForIndex.get(n);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] prices = new int[]{1, 5, 8, 9, 10, 17, 17, 20};
		
		int i = cutting(prices, 8);
		System.out.println(i);
	}

}
