package com.algo.test;

/**
 * 求两个单词之间的编辑距离。
 * 编辑操作方法分为三种：
 * 1. 插入一个字符
 * 2. 删除一个字符
 * 3. 替换一个字符
 * 
 * 实现方式：
 * @author guozhen@proudsmart.com
 *
 */

public class EditDistance {

	/**
	 * 求将w1变成w2所需要的最少的操作次数。
	 * 例如：cafe到coffee，所需要的最少编辑次数是：
	 * 将cafe中的a替换成o
	 * f后插入一个f
	 * e后插入一个e
	 * 则cafe到coffee的编辑距离为3
	 * 
	 * 解决思路：
	 * 1. 假设我w1长度为l1，w2长度为l2，则创建一个(l1+2)*(l2+2)的表：
	 * | | |c|o|f|f|e|e|
	 * | | | | | | | | |
	 * |c| | | | | | | |
	 * |a| | | | | | | |
	 * |f| | | | | | | |
	 * |e| | | | | | | |
	 * 在Java的实现中， 实际上相当于一个(l1+1)*(l2+1)的二维数组
	 * 
	 * 2. 在表中填入一下数字：
	 * | | |c|o|f|f|e|e|
	 * | |0|1|2|3|4|5|6|
	 * |c|1| | | | | | |
	 * |a|2| | | | | | |
	 * |f|3| | | | | | |
	 * |e|4| | | | | | |
	 * 相当于对二维数组进行初始化。
	 * 
	 * 3. 从3*3格开始计算(对于数组，是1*1)，该格的值的计算规则为：
	 * 如果该格最上方的字符等于最左方的数字，则将该格的值设置为左上角的值；
	 * 否则通过计算以下三个值，获取其最小值：
	 * (1)否则，左上角的数字+1
	 * (2)左边数字+1
	 * (3)上边数字+1
	 * 计算完成后，取这三个值中的最小值，设置为当前格的值。
	 * 
	 * 4. 计算3*4格，然后3*5，最后到下一行，以此类推，直到计算到最后一个格。此时最后一个格中
	 * 的数字就是要求的编辑距离。
	 * 
	 * @param w1
	 * @param w2
	 * @return
	 */
	public static int getEditDistance(String w1, String w2){
		//定义二维数组
		int[][] nums = new int[w1.length()+1][w2.length()+1];
		/**
		 * 2. 在表中填入一下数字：
		 * | | |c|o|f|f|e|e|
		 * | |0|1|2|3|4|5|6|
		 * |c|1| | | | | | |
		 * |a|2| | | | | | |
		 * |f|3| | | | | | |
		 * |e|4| | | | | | |
		 * 相当于对二维数组进行初始化。
		 */
		//初始化二维数组：
		for(int i = 0; i < nums[0].length ; i++){
			nums[0][i] = i;
		}
		
		for(int i = 0; i < nums.length; i++){
			nums[i][0] = i;
		}
		
		printArray(nums);
		
		for(int i = 1; i < nums.length; i++){
			for(int j = 1; j < nums[0].length; j++){
				//如果该顶点的左边和上边数字相同，则取左上方的数字
				if(w1.charAt(i-1) == w2.charAt(j-1)){
					nums[i][j] = nums[i-1][j-1];
				}else{
					//否则，取将格中的值设置为：左边的数字, 上边的数字和左上方数字，比较来获取三个值中的最小值，然后+1
					nums[i][j] = min(nums[i][j-1], nums[i-1][j-1], nums[i-1][j])+1;
				}
				
				printArray(nums);
			}
		}
		
		printArray(nums);
		
		return nums[w1.length()-1][w2.length()-1];
	}
	
	public static void main(String[] args) {
		int distance = getEditDistance("cafe", "coffee");
		System.out.println(distance);
	}

	private static int min(int i, int j, int k) {
		int min = i;
		if(min > j){
			min = j;
		}
		
		if(min > k){
			min = k;
		}
		return min;
	}
	
	
	private static void printArray(int[][] nums){
		for(int i = 0; i < nums.length; i++){
			for(int j = 0; j < nums[0].length; j++){
				System.out.print(nums[i][j]+",");
			}
			System.out.println();
		}
		
		System.out.println("---------------------------");
	}
}
