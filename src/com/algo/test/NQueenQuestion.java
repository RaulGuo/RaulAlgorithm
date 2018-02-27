package com.algo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题。
 * N皇后问题代表的含义是：将N个皇后放到一个N*N的棋盘上，皇后
 * 两两之间不能互相攻击。（不互相攻击的含义是：在Queen所在
 * 的横纵线上不能有其他的Queen。在一个Queen的四周不能有其他的Queen。）
 * 例如：四皇后情况下，解决方案有两种：
 * 1.
 * .Q..
 * ...Q
 * Q...
 * ..Q.
 * 
 * 2.
 * ..Q.
 * Q...
 * ...Q
 * .Q..
 */

public class NQueenQuestion {
    /*
     * @param n: The number of queens
     * @return: All distinct solutions
     * 解决思路：
     * 1. 设置一个长度为N的数组。
     * 2. 将第一个元素放置在第一个位置上。元素放定之后，则该元素横向所占的位置对应数组中的位置被占据。记住当前元素的位置。
     * 3. 放置下一个元素，要遵守以下原则：（1）不跟上一个元素的index重位（2）此元素index减去上一个元素的index的绝对值大于等于2
     * 
     */
	
    public static List<int[]> placeQueen(int n) {
    	List<int[]> list = new ArrayList<int[]>();
    	//将数组的初始值设为-1，代表该元素的位置尚未确定（-1还有一个好处是：在while循环里，对元素的位置地形递增操作时需要+1，-1进行+1操作恰好是0）。
    	int initPosition = -1;
    	
    	int[] positions = new int[n];
    	for(int i = 0; i < positions.length; i++){
    		positions[i] = initPosition;
    	}
    	
    	int k = 0;//从第1行开始
    	//用while循环或者for循环都可以实现回溯算法。
    	while(k >= 0){
    		//如果k的位置不合适，就继续循环
    		boolean continueFlag = false;
    		//这里执行的+1操作，既代表元素的初始化（既-1+1=0），又代表在回溯时，原有的值对应的情况已经探索
    		//完成，所以这里重新探索下一轮
    		positions[k] = positions[k]+1;
    		//不断寻找当前列的可用位置，如果找不到，就累加；
    		//当出现非法值时，说明当前列所在的位置无法找到合法的解，执行回溯。
    		//由于这里是双重循环，所以需要continueFlag，使得在当前while循环中
    		//发生了回溯时，执行continue，也就是继续外层的循环。
    		while(positions[k] < n && !canPlace(positions, k)){
    			//position[k]就是第k行的元素的位置。
    			positions[k] = positions[k] + 1;
    			//如果一直找到n仍然不行（n已经是非法值），则需要回溯。
    			if(positions[k] >= n){
    				positions[k] = initPosition;
    				k--;//while循环中的变量的反向操作（正常是++,回溯就是--）正是回溯实现的关键点。
    				continueFlag = true;
    				break;
    			}
    		}
    		
    		//如果上边的循环中出现了回溯，就跳过之后的操作，继续循环。
    		if(continueFlag)
    			continue;
    		if(k == n-1 && positions[k] < n){
    			//如果找到了最后的位置，说明发现了一个可用解，将该可用解保存。
    			list.add(positions.clone());
    		}else if(k < n-1 && positions[k] < n){
    			//如果还没有探索到最后一行，则此时还可以继续向下寻找，找到下一行元素可以使用的点
    			k++;
    		}else{
    			//如果找到了最后位置还没找到可用解，说明当前路走不通，需要执行回溯。
    			positions[k] = initPosition;
    			k--;
    		}
    		
    	}
    	
    	return list;
    }

    /**
     * 判断某个元素是否可以放置的棋盘上的某个位置。
     * @param positions: 元素当前的摆放位置。
     * @param currPosition: 此元素是否可以用某个位置。
     * @return
     */
    public static boolean canPlace(int[] positions, int currPosition){
    	for(int i = 0; i < currPosition; i++){
    		//如果i位置被占了，
    		if(positions[i] == positions[currPosition] || currPosition-i == Math.abs(positions[i]-positions[currPosition])){
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
		List<int[]> list = placeQueen(5);
		list.forEach(x -> {
			for(int i = 0; i < x.length; i++){
				System.out.print(x[i]+",");
			}
			System.out.println();
		});
	}
}
