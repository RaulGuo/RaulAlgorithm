package com.algo.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * 筛子求和的问题。
 * 扔n个筛子，向上的数字之和为S。给定N，求S所有可能的值以及每个只对应的概率。
 * http://www.lintcode.com/zh-cn/problem/dices-sum/
 * @author guozhen@proudsmart.com
 *
 */

public class DiceSumPossiblity {
	
	/**
	 * 使用回溯法，将扔times次骰子的所有情况都遍历出来，并将其和放到map中。map中将每个和出现的次数记录下来。
	 * @param times
	 * @return
	 */
	private static Map<Integer, Integer> getAllWays(int times){
		int[] rowValues = new int[times];
		Map<Integer, Integer> mapResult = new HashMap<Integer, Integer>();
		int currTime = 0;
		while(currTime < times){
			//如果是在最后一行，则将currTime在rowValues中的值设置为1到6并分别记录其和。然后向上回溯一行。
			if(currTime == times-1){
				for(int i = 1 ; i <= 6; i++){
					rowValues[currTime] = i;
					int sum = sumIntArr(rowValues);
					if(mapResult.containsKey(sum)){
						mapResult.put(sum, mapResult.get(sum)+1);
					}else{
						mapResult.put(sum, 1);
					}
				}
				currTime--;
			}else{
				//如果不是最后一行，需要看当前行的值是否已经遍历到了6。
				if(rowValues[currTime] == 6){
					//如果当前行的值已经到了最大值，判断是否当前行是第一行。
					if(currTime == 0){
						//如果是第一行，代表回溯算法已经结束，返回结果即可。
						return mapResult;
					}else{
						//如果不是第一行，将当前行的值设为初始值，并回溯一行。
						rowValues[currTime] = 0;
						currTime--;
					}
				}else{
					//如果当前行还没有遍历到6，将当前行的值进行+1操作，并向下回溯。
					rowValues[currTime]++;
					currTime++;
				}
			}
		}
		
		return mapResult;
	}
	
	public static int sumIntArr(int[] arr){
		int sum = 0;
		for(int i = 0; i < arr.length; i++){
			sum = sum+arr[i];
		}
		return sum;
	}
	
	
	private static Map<Integer, Double> getPossibility(Map<Integer, Integer> map){
		Integer sum = map.values().stream().reduce(new BinaryOperator<Integer>(){

			@Override
			public Integer apply(Integer i, Integer j) {
				return i+j;
			}
			
		}).get();
		
		Map<Integer, Double> result = new HashMap<Integer, Double>();
		map.keySet().forEach(x -> {
			result.put(x, (map.get(x)+0.0)/sum);
		});
		
		return result;
	}
	
	public static void main(String[] args) {
		Map<Integer, Integer> allPossibilities = getAllWays(5);
		Map<Integer, Double> possibles = getPossibility(allPossibilities);
		
		for(Integer key: possibles.keySet()){
			System.out.println("key:"+key+" -> possibility:"+possibles.get(key));
		}
	}
	
}
