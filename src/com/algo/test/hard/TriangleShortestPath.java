package com.algo.test.hard;

/**
 * 一个数字三角形，寻找一条从顶部到底边的路径，使得路径上所经过的数字之和最大，获取这个和（而不需要路径）
 *  
 * 例如：
 *       7
 *      3 8
 *     8 1 0
 *    2 7 4 4 
 *   4 5 2 6 5
 *   
 * 该三角形的最长路径为：7 -> 3 -> 8 -> 7 -> 5
 *  
 * @author guozhen@proudsmart.com
 *
 */
public class TriangleShortestPath {

	public static void main(String[] args) {
		int[][] triangle = new int[5][5];
		triangle[0][0] = 7;
		triangle[1][0] = 3;
		triangle[1][1] = 8;
		triangle[2][0] = 8;
		triangle[2][1] = 1;
		triangle[2][2] = 0;
		triangle[3][0] = 2;
		triangle[3][1] = 7;
		triangle[3][2] = 4;
		triangle[3][3] = 4;
		triangle[4][0] = 4;
		triangle[4][1] = 5;
		triangle[4][2] = 2;
		triangle[4][3] = 6;
		triangle[4][4] = 5;
		
		
		int maxVal = maxPathValueByRecurrence1D(triangle);
		System.out.println(maxVal);
		
	}
	
	/**
	 * 通过递归的方式来获取最长路径。与maxPathValue的区别在于，
	 * 该方式通过一个memorySum避免了对同一个顶点的最长路径的重复计算。
	 * 但本质上仍然是通过递归调用的方式来获取顶点最长路径。
	 * 
	 * @param triangle
	 * @return
	 */
	public static int maxSumWithMemory(int[][] triangle){
		int[][] memorySum = new int[triangle.length][triangle.length];
		for(int i = 0; i < triangle.length; i++){
			memorySum[triangle.length-1][i] = triangle[triangle.length-1][i];
		}
		
		
		return maxSum(triangle, 1, 1, memorySum);
	}
	
	
	public static int maxSum(int[][] triangle, int x, int y, int[][] memorySum){
		if(memorySum[x-1][y-1] != 0){
			return memorySum[x-1][y-1];
		}
		
		if(x == triangle.length){
			return triangle[x-1][y-1];
		}else{
			int max1 = maxSum(triangle, x+1, y, memorySum);
			int max2 = maxSum(triangle, x+1, y+1, memorySum);
			int max = Math.max(max1, max2) + triangle[x-1][y-1];
			memorySum[x-1][y-1] = max;
			return max;
		}
		
		
	}
	
	/**
	 * 采用递归的方式来求路径的最大值。
	 * 假设D(i, j)代表第i行的第j个数字（i,j都从1开始算）。
	 * 假设MaxSum(i, j)代表从D(i,j)到底边的各条路径中最佳路径的和。
	 * 如果i=n（n代表这个三角形的高度，也就是第二个维度的长度），则MaxSum(i,j)=D(i,j)
	 * 从D(i, j)走下一步只能走D(i+1,j)或者D(i+1, j+1)
	 * 所以MaxSum(i,j) = Max(MaxSum(i+1, j), MaxSum(i+1, j+1)) + D(i,j)
	 * 
	 * 递归方法的问题：大量重复计算。
	 * 除了递归方法固有的对内存的大量消耗的缺陷外，这种实现方式还有另外一个问题在于：
	 * 大量的重复计算。例如：对(3,3)这个节点的最大值的计算是无法记录的，所以，在计算(2,2)和(2,3)
	 * 这两个节点的MaxValue的计算时，他们分别需要计算一遍(3,3)的MaxValue。这个状态无法记录导致了大量的重复计算。
	 * 
	 * 
	 * @param triangle
	 * @return
	 */
	public static int maxPathValue(int[][] triangle){
		return maxByCoordinate(triangle, 1, 1);
	}
	
	public static int maxByCoordinate(int[][] triangle, int x, int y){
		if(x == triangle.length){
			return triangle[x-1][y-1];
		}else{
			int val1 = maxByCoordinate(triangle, x+1, y);
			int val2 = maxByCoordinate(triangle, x+1, y+1);
			return Math.max(val1, val2) + triangle[x-1][y-1];
		}
	}
	
	/**
	 * 使用递推的方式来计算从上到下的最长路径。
	 * 思路：
	 * 使用一个二维数组来记录每个顶点的最长路径的长度。最后一行的最长路径就是该顶点的值本身。
	 * 然后递推计算倒数第二行的各个顶点的最长路径。之后递推倒数第一行。
	 * 
	 * 这种方法可以看做动态规划的算法。动态规划的方程为：
	 * 当i == n时，
	 * Max[i][j] = triangle[i][j]
	 * 否则：
	 * Max[i][j] = max(Max[i+1][j+1], Max[i+1][j]) + triangle[i][j]
	 * @param triangle
	 * @return
	 */
	public static int maxPathValueByRecurrence(int[][] triangle){
		
		int[][] maxSum = new int[triangle.length][triangle.length];
		//计算最后一行的最长路径
		for(int i = 0; i < triangle.length; i++){
			maxSum[triangle.length-1][i] = triangle[triangle.length-1][i];
		}
		
		//从倒数第二行递推到第一行，求出最短路径。
		for(int i = triangle.length-2; i >= 0; i--){
			for(int j = 0; j <= i; j++){
				int max1 = maxSum[i+1][j];
				int max2 = maxSum[i+1][j+1];
				int max = Math.max(max1, max2)+triangle[i][j];
				maxSum[i][j] = max;
			}
		}
		
		return maxSum[0][0];
	}
	
	/**
	 * 用递推的方式，只用一个一维数组来记录最长路径。
	 * 与maxPathValueByRecurrence方法的区别在于：不需要一个二维数组来记录最长路径。
	 *   7
	 *  3 8
	 * 8 1 0
	 *2 7 4 4 
 *   4 5 2 6 5
 *   
 *   先用一个数组记录最后一行的最长访问长度：[4,5,2,6,5]
 *   计算倒数第二行的第一个元素的最长访问长度时，直接修改第一个元素：[7,5,2,6,5]
 *   可以这么计算的原因在于：每一行的第i个元素的最长路径长度只依赖于下一行中第i和第i+1个元素。
	 * @param triangle
	 * @return
	 */
	public static int maxPathValueByRecurrence1D(int[][] triangle){
		
		int[] maxSum = new int[triangle.length];
		//计算最后一行的最长路径
		for(int i = 0; i < triangle.length; i++){
			maxSum[i] = triangle[triangle.length-1][i];
		}
		
		//从倒数第二行递推到第一行，求出最短路径。
		for(int i = triangle.length-2; i >= 0; i--){
			for(int j = 0; j <= i; j++){
				int max1 = maxSum[j];
				int max2 = maxSum[j+1];
				int max = Math.max(max1, max2)+triangle[i][j];
				maxSum[j] = max;
			}
		}
		
		return maxSum[0];
	}
}
