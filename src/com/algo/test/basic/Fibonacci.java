package com.algo.test.basic;

public class Fibonacci {
	
	//使用递归的方式获取第n个斐波那契数列的数字
	public static int getNFiboRecurssive(int n){
		if(n == 0 || n == 1)
			return 1;
		else
			return getNFiboRecurssive(n-1)+getNFiboRecurssive(n-2);
	}
	
	//使用递推的方式去获取第n个斐波那契数。使用两个变量去记录要求的斐波那契数的前两个斐波那契数字。
	//然后一个一个的递推去求。
	public static int getNFiboByRecurrence(int n){
		if(n == 0 || n == 1)
			return 1;
		int first = 1;
		int next = 1;
		for(int i = 2; i < n; i++){
			int tmp = next + first;
			first = next;
			next = tmp;
		}
		
		return first+next;
	}
	
	public static void main(String[] args) {
		int index = 2;
		for(int i = 0 ; i < 10; i++)
			System.out.print(getNFiboByRecurrence(index++)+",");
	}
}
