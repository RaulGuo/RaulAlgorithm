package com.algo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 从1开始，找出第n个丑数。
 * 丑数的定义是：只包含素因子2,3,5的数。1也可以算作丑数。
 * 其他符合条件的树包括：2,3,4,5,6,8,9,10,12等。
 * 
 * 丑数的判断方法：
 * 首先除2，直到不能整除为止；如果最后结果为1，则是丑数；否则，去整除后的值（不能整除则取原值），继续以下的计算；
 * 然后除3，直到不能整除为止；如果最后结果为1，则是丑数；否则，去整除后的值（不能整除则取原值），继续以下的计算；
 * 最后除5，直到不能整除为止；如果最后结果为1，则是丑数；
 * 如果以上条件都不满足，就不是丑数。
 * 
 * http://www.lintcode.com/zh-cn/problem/ugly-number-ii/
 * 
 * @author guozhen@proudsmart.com
 *
 */

public class UglyNumber {
	
	public static boolean isUglyNumber(int num){
		while(num%2 == 0){
			num = num/2;
		}
		
		if(num == 1){
			return true;
		}
		
		while(num%3 == 0){
			num = num/3;
		}
		
		if(num == 1){
			return true;
		}
		
		while(num%5 == 0){
			num = num/5;
		}
		
		if(num == 1){
			return true;
		}
		
		return false;
	}
	
	public static List<Integer> getTopNUglyNumber(int n){
		int count = 0;
		int currNum = 1;
		
		List<Integer> list = new ArrayList<Integer>(n);
		
		while(count < n){
			for(;;currNum++){
				if(isUglyNumber(currNum)){
					count++;
					list.add(currNum++);
					break;
				}
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		List<Integer> list = getTopNUglyNumber(20);
		list.forEach(System.out::println);
	}
	
	
}
