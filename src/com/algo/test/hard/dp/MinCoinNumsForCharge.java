package com.algo.test.hard.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * 硬币找零问题。
 * 给与不同面值的阴面若干种，每种无限多个，用若干硬币组合成某个面额的钱，使硬币数量最少。
 * 例如，给定零钱1,2,3,5,10，现在要凑够18元钱。
 * @author guozhen@proudsmart.com
 *
 */
public class MinCoinNumsForCharge {
	
	/**
	 * 采用动态规划的方法来计算最少的找零数量。
	 * 思路：
	 * 定义状态为：当用最大面额为i的硬币，去凑齐j元时，最少的零钱数量为：a[i][j]。我们需要一个二维数组去记录这个状态。
	 * 二维数组的行数为面额的数量，列数为charge+1。
	 * 状态转换方程的定义如下：
	 * 对于面额为i的硬币，假设使用它以及他之前的硬币，凑齐j的最少的找零数量为：a[i][j]，则求a[i][j]的值有以下几种情形：
	 * 如果不使用面额为i的硬币，则a[i][j] = a[i-1][j]
	 * 如果使用一张面额为i的硬币，则a[i][j] = a[i-1][j-coins[i]]+1
	 * 如果使用两张i，则：a[i][j] = a[i-1][j-coins[i]*2]+2
	 * ....
	 * 如果使用n张i，则：a[i][j] = a[i-1][j-coins[i]*n]+n
	 * 
	 * 我们需要取以上的值中最小的值，作为a[i][j]的值。
	 * 综上，状态转移方程为：
	 * a[i][j] = min(a[i-1][j], min(a[i-1][j-k*coins[i]]+k))
	 * 
	 * 初始状态的定义为：面额为i的硬币，在找零钱为i*n时，所需要的最少硬币数量是n。所以a[i][i] = 1.
	 * 
	 * 而我们要求的，就是a[coins.length][charge]。也就是当前coins.length个硬币可用（其实就是都可用），去凑齐最大面额为charge时，
	 * 最少硬币的数量。
	 * 
	 * 从结果来看，这个解法不如递推方式，主要是难以理解，且包含了许多无效操作。
	 * 但这个计算过程可以减少一个数组维度来实现。
	 * @param coins
	 * @param charge
	 * @return
	 */
	public static int getNumsOfCoinsByDP(int[] coins, int charge){
		int[][] matrix = new int[coins.length][charge+1];
		
		//没什么意义，为了打印出来更直观。第一排的值没含义
		for(int i = 0; i < coins.length; i++){
			matrix[i][0] = coins[i];
		}
		
		//初始状态的定义
		for(int i = 0; i < coins.length; i++){
			for(int j = 1; j < charge+1; j++){
				if(j%coins[i] == 0){
					matrix[i][j] = j/coins[i];
				}else{
					matrix[i][j] = -1;
				}
			}
		}
		
		printMatrix(matrix);
		
		for(int i = 1; i < coins.length; i++){
			for(int j = 1; j <= charge; j++){
				//如果不使用面额为coins[i]的硬币，则其对应的最小值为matrix[i-1][j]
				if(matrix[i][j] == -1 && j > coins[i]){
					int min = matrix[i-1][j];
					//n代表取n个面额为coins[i]的硬币数量
					for(int n = 1; ; n++){
						if(coins[i]*n > j)
							break;
						else if(matrix[i-1][j-coins[i]*n] != -1)
							min = Math.min(min, matrix[i-1][j-coins[i]*n]+n);
					}
					matrix[i][j] = min;
				}
				printMatrix(matrix);
			}
			printMatrix(matrix);
		}
		
		return matrix[coins.length-1][charge];
		
	}
	
	/**
	 * 采用简单的递推的方法来寻找最少的硬币数量。
	 * 用一个集合代表这一轮可能的剩余找零的面额。
	 * 如果集合中的某个额度跟面额恰好相等，代表下一轮就可以凑齐零钱了。
	 * 
	 * @param coins
	 * @param charge
	 * @return
	 */
	public static int getNumsOfCoins(int[] coins, int charge){
		Set<Integer> leftPossibleCharge = new HashSet<Integer>();
		leftPossibleCharge.add(charge);
		
		boolean findFlag = false;
		int count = 1;
		while(!findFlag){
			Set<Integer> possibleChargeForCurr = new HashSet<Integer>();
			for(Integer leftCharge: leftPossibleCharge){
				for(int i = 0; i < coins.length;i++){
					int chargeForCurrCoins = leftCharge - coins[i];
					if(contains(coins, chargeForCurrCoins)){
						return count+1;
					}else{
						possibleChargeForCurr.add(chargeForCurrCoins);
					}
				}
			}
			count++;
			leftPossibleCharge = possibleChargeForCurr;
		}
		
		return -1;
	}
	
	private static boolean contains(int[] coins, int coin){
		for(int i = 0; i < coins.length; i++){
			if(coins[i] == coin){
				return true;
			}
		}
		
		return false;
	}
	
	
	private static void printMatrix(int[][] matrix){
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				System.out.print(matrix[i][j]+",\t");
			}
			System.out.println();
		}
		System.out.println("------------------------");
	}
	
	//-1是数组中的初始值，不算数。
	private static int min(int i, int j){
		if(i == -1)
			return j;
		else if(j == -1){
			return i;
		}else{
			return Math.min(i, j);
		}
	}
	
	public static void main(String[] args) {
		int[] coins = new int[]{1,2,3,5,9,10};
		int charge = 18;
		int num = getNumsOfCoinsByDP(coins, charge);
		System.out.println(num);
	}
}
