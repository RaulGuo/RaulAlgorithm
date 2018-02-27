package com.algo.testcopy;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题重新算一遍。
 * @author guozhen@proudsmart.com
 *
 */

public class NQueenCopy {
	
	/**
	 * 回溯法获取N皇后问题的所有可能的解。
	 * @param n
	 * @return
	 */
	public static List<int[]> getNQueenSolutions(int n){
		int[] positions = new int[n];
		//positions中，每个index的值为-1代表该行还没有被皇后占用。
		//其余的值代表第n行中的皇后摆在了index上。
		for(int i = 0; i < positions.length; i++){
			positions[i] = -1;
		}
		
		List<int[]> result = new ArrayList<int[]>();
		
		int currRow = 0;
		
		/**
		 * 从第一行开始，逐行寻找在每一行的可用的位置。
		 * 	-> 找到一个可用位置之后，记录当前位置到positions，然后去找寻下一行。
		 * 	-> 如果一直找到最后也没有可用位置，说明这种情况非法。返回上一行，将上一行中使用的记录的位置+1。
		 *  -> 如果当前行已经是最后一行，则找到一个合法位置后，将positions添加到list中。添加后，继续寻找狭义合法位置。
		 *   	-> 如果已经找到最后 
		 * 如果在这一行
		 */
		
		while(currRow < n){
			//试试看在当前的行中，哪个位置是合法的。找到一个合法的值之后，继续到下一行中找下一行的可用的值。
			boolean findFlag = false;
			
			//回溯回来发现当前行已经到最后了，就继续向上回溯。
			if(positions[currRow] == n-1){
				positions[currRow] = -1;
				currRow--;
				continue;
			}
			
			positions[currRow] = positions[currRow]+1;
			//如果当前的行中的值是-1，代表此行没有尝试过任何值，则可以从0开始尝试。
			//如果当前行中的值是-1之外的值，代表比当前值小的其他数都已经尝试过了。则直接从下一个值开始即可。
			//如此，恰好这两种操作的情况都是要执行+1操作。
			//所以，for循环中i的值统一为positions[currRow]+1
			//在回溯算法中，回溯的起始代码中尤其要注意。写代码时，在回溯点时往往是以算法刚开始执行时的思路去写的，
			//但实际执行时，绝大多数是代码执行过程中回溯到此的，此时的情形可能跟代码刚开始运行时不一样。
			//例如，在这个例子中，很容易就会在for循环中，设置初始条件为i=0。但实际上，回溯回来时，这里的currRow上常常是有了值的。
			//如果在这里写了初始条件i=0，在后边回溯回来时，这里就有了矛盾。所以如果有了矛盾，就想想是不是有些初始值设置的有问题。
			for(int i = positions[currRow]; i < positions.length; i++){
				if(canPlace(currRow, i, positions)){
					positions[currRow] = i;
					findFlag = true;
					break;
				}
			}
			
			if(findFlag){
				//在当前行找到一个合法值。如果当前行已经是最后一行，则直接添加到结果列表中，然后回溯。
				//如果不是最后一行，则继续寻找下一行。
				if(currRow == n-1){
					result.add(positions.clone());
				}else{
					currRow++;
				}
			}else{
				//当前行没有合法的值，回溯一行。如果当前已经是最后一行，无法回溯，直接返回结果即可。
				if(currRow == 0){
					return result;
				}
				currRow--;
			}
			
		}
		
		return result;
	}
	
	
	
	public static List<int[]> getSolutions(int n){
		List<int[]> result = new ArrayList<int[]>();
		final int init = -1;
		//数组中每个元素的值代表了第index行的皇后所摆放的位置。
		int[] positions = new int[n];
		for(int i = 0; i < positions.length; i++){
			positions[i] = init;
		}
		
		
		int currRow = 0;
		
		//回溯的起始点。
		while(currRow < n){
			//当回溯到这里时，先要检查是否这一行的回溯也已经到头了（标记是：currRow在positions中的值已经达到了最大值）。
			if(positions[currRow] == n-1){
				//如果这一行的回溯到头了，检查是否当前行已经是第一行。如果是，代表第一行回溯已经到了最后，返回结果即可。
				if(currRow == 0){
					return result;
				}else{
					//如果这一行已经到头了，还不是第一行，则将当前的行的值设置为初始值，继续向上一行回溯。
					positions[currRow] = init;
					currRow--;
					continue;
				}
			}
			
			//如果是一般的向下回溯。从此行的下一个值开始，找一个合法值；如果当前行已经是最后一行，则保存结果，否则继续向下回溯。
			//如果没有找到合法值，向上回溯。
			boolean findFlag = false;
			for(int i = positions[currRow]+1; i < n; i++){
				if(canPlace(currRow, i, positions)){
					positions[currRow] = i;
					findFlag = true;
					break;
				}
			}
			
			if(findFlag){
				//如果找到一个适用的元素，判断下是否已经是最后一行。
				if(currRow >= n-1){
					//是最后一行的话，直接加入list中，然后不修改任何条件，在当前行继续回溯
					result.add(positions.clone());
				}else{
					//如果当前行不是最后一行的话，继续向下回溯。
					currRow++;
				}
				continue;
			}else{
				if(currRow == 0){
					//如果第一行中没有找到其他的可用元素，直接返回结果；
					return result;
				}else{
					//如果这一行（第一行之外的其他的行）没有找到其他的合适的元素，把这一行的元素设为原始值，向上回溯一行。
					positions[currRow] = -1;
					currRow--;
				}
			}
			
		}
		
		
		return result;
	}
	
	/**
	 * 检测一个queen是否某个在currRow行，position列上摆放。
	 * 如果跟已有的任意的queen有冲突（跟已有的queen在同一行，同一列，或者对角线上），则不能摆放。
	 * @param currRow
	 * @param position
	 * @param positions
	 * @return
	 */
	public static boolean canPlace(int currRow, int currPosition, int[] positions){
		//从0到n-1逐个检测，看看该位置是否可用
		for(int i = 0; i < positions.length; i++){
			//如果该位置没有被占用，且
			if(positions[i] != -1 && (positions[i] == currPosition || Math.abs(currRow-i) == Math.abs(currPosition-positions[i])))
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		List<int[]> list = getSolutions(4);
		list.forEach(x -> {
			for(int i = 0; i < x.length; i++){
				System.out.print(x[i]+",");
			} 
			System.out.println();
		});
	}	
}
