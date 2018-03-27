package com.algo.test.hard;

/**
 * 最大矩形问题：
 * 给你一个二维矩阵，权值为False和True，找到一个最大的矩形，
 * 使得里面的值全部为True，输出它的面积
 * 
 * 给你一个矩阵如下

[
  [1, 1, 0, 0, 1],
  [0, 1, 0, 0, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 0, 0, 1]
]
 *
 * 输出面积为6
 * 
 * 
 * https://leetcode.com/problems/maximal-rectangle/description/
 * http://www.lintcode.com/zh-cn/problem/maximal-rectangle/
 * @author guozhen@proudsmart.com
 *
 */
public class MaxRectangleOfMatrix {
	
	/**
	 * 递归计算每个顶点的最大面积，保留最大的面积。
	 * 
	 * @param matrix
	 * @return
	 */
	public static int maxAreaOfRectangle(boolean[][] matrix){
		int maxArea = 0;
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				int area = getMaxAreaOfVertex(matrix, i, j);
				if(maxArea < area){
					maxArea = area;
				}
			}
		}
		
		return maxArea;
	}
	
	/**
	 * 求从一个顶点开始，向右向下递归寻找最大的面积。不需要向左向上是因为向左向上的情况在之前的遍历已经完成了。
	 * 时间复杂度：O(n²)
	 * 
	 * @param matrix
	 * @param x
	 * @param y
	 * @return
	 */
	public static int getMaxAreaOfVertex(boolean[][] matrix, int x, int y){
		if(!matrix[x][y]){
			return 0;
		}
		
		//length代表当前计算时，求出的长方形的长度。限制于该长度，下一行在遍历时，不可以超出这个长度。
		int length = Integer.MAX_VALUE, width = 1;
		int tmpX = x;
		int area = 1;
		
		//如果在新一行中，第一个元素为true，就可以对这一行进行遍历，求加上这一行后，可能的最大面积
		while(tmpX < matrix.length && matrix[tmpX][y]){
			//tmpLength代表这一行在满足小于长方形最大长度的基础上，所能获取的最大长度
			int tmpLength = 1;
			//在这个算法中，比较特殊之处在于，y是需要保留的，y的值在每次开启新的一行时，都需要从初始值开始。所以需要一个tmpY
			int tmpY = y+1;
			
			//计算在这一行中，从第y个元素开始，有连续多少个元素为true。注意数组下标可能越界，且长度不能大于之前确定的最大长度（长方形的要求）。
			while(tmpLength <= length && tmpY < matrix[0].length && matrix[tmpX][tmpY]){
				tmpLength++;
				tmpY++;
			}
			
			//与原有的最大面积相比较，保留较大的面积。
			if(area < tmpLength*width){
				area = tmpLength*width;
			}
			
			//这一行遍历完成后，遍历下一行；遍历下一行时，宽度自动+1。
			width++;
			tmpX++;
			//tmpLength在这里必然小于等于length，如果小于，应该将length的值设置为tmpLength。长方形的宽度应该是短板原理。
			length = tmpLength;
		}
		
		return area;
	}
	
	/**
	 * 通过动态规划算法求出最大面积。
	 * 
	 * 定义状态转移方程如下：
	 * left[i][j] = max(left(i-1, j), curr_left)
	 * 
	 * 我们定义三个数组：left，right，height，长度为matrix中一行的长度（也就是matrix[0].length）。
	 * 
	 * 
	 * https://leetcode.com/problems/maximal-rectangle/discuss/29054/share-my-dp-solution
	 * @param matrix
	 * @return
	 */
	public static int maxAreaByDP(boolean[][] matrix){
		int max = 0;
		
		return 0;
	}
	
	public static void main(String[] args) {
		boolean[][] matrix = {
				{true, true, false, false, true},
				{false, true, false, false, true},
				{false, false, true, true, true},
				{false, false, true, true, true},
				{false, false, true, true, false}};
		int area = maxAreaOfRectangle(matrix);
		System.out.println(area);
	}
	
}

