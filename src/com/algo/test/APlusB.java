package com.algo.test;

/**
 * 不使用+运算符实现加法运算，思路是用位操作符。
 * 1. 对于二进制的加法，在不考虑进位的情况下0+0和1+1都为1,0+1和1+0都为0，所以可以看做异或的运算。
 * 2. 对于进位的情况下，只有1+1才会产生进位，也就是一种逻辑与的运算。逻辑与运算后，由于要进位，所以应该左移一位。
 * 3. 将以上两个数进行异或计算，得到的就是加法的结果。
 * 
 * 但该方法不适用于负数的情形。
 * @author guozhen@proudsmart.com
 *
 */
public class APlusB {

	public static int aplusb(int a, int b){
		//计算异或
		int x = a^b;
		//计算逻辑与，并左移一位。要注意运算顺序，左移的优先级高于逻辑与，所以要用括号。
		int y = (a&b) << 1;
		//计算异或
		return x^y;
	}
	
	public static void main(String[] args) {
		System.out.println(aplusb(100, -100));
	}

}
