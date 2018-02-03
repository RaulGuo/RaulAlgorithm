package com.algo.test;

public class DigitCount {
	/**
	 * k代表0到n之间的一个数。
	 * 求从0到n的所有整数中，k出现的次数。
	 * 
	 * @param k
	 * @param n
	 * @return
	 */
	public static int digitCount(int k, int n){
		int count = 0;
		for(int i = 0; i <= n ; i++){
			int tmp = i;
			do{
				if(tmp%10 == k){
					count++;
				}
				tmp = tmp/10;
			}while(tmp != 0);
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(digitCount(1, 12));
	}
}
