package com.algo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个可能包含重复元素的数组，找出所有可能的排列。
 * http://blog.csdn.net/shineboyxxb/article/details/52163202
 * @author guozhen@proudsmart.com
 *
 */
public class DistinctArrangeOfArray {
	
	/**
	 * 计算思路：
	 * 1. 先将数组进行排序，可以使用冒泡排序或者其他的排序方法。此时，可以将排序后的数组看做最小数组。
	 * 2. 基于该最小数组，找到下一个最小数组，找到的方法是：
	 * 
	 *** 1. 将数组从右到左依次遍历比较，找到第一个nums[i+1]>nums[i]的元素，将i+1标记为pivot（中心点）
	 *** 2. 从右向左，找到第一个大于pivot的元素，将其标记为change。交换pivot和change的位置。
	 *** 3. 将pivot之后的数组进行一次反转。
	 * 
	 * 此时，该数组就是下一个最小数组。然后重复此步骤，直到找不到最小数组（找不到最小数组的判断依据是：
	 * pivot的值没有重置，则此时数组已经是完全的逆向数组）。
	 * 
	 * 注意点：
	 * List在添加数组时，保存的实际上是对象的指针。所以list在添加了数组之后，如果数组的值发生了变化，
	 * 则list中的值也会发生改变。要让list中添加的元素在添加之后就不再变化，可以使用数组的clone。
	 * 
	 * 
     * @param : A list of integers
     * @return: A list of unique permutations
     */
    public static List<int[]> permuteUnique(int[] nums) {
    	List<int[]> list = new ArrayList<int[]>();
    	//1. 先对数组进行由小到大的冒泡排序
    	nums = bubbleSort(nums);
    	list.add(nums.clone());
    	
    	while((nums = getNextByOrder(nums)) != null){
    		int[] tmp = nums.clone();
    		list.add(tmp);
    	}
    	
    	
    	return list;
    }
    
	public static void main(String[] args) {
//		int[] nums = new int[]{6,3,20,4,7,3,1,6,1,0,0,20,5,6};
		int[] nums = {2,1,2,3};
		List<int[]> list = permuteUnique(nums);
		
		list.forEach(x -> {
			for(int i = 0; i < x.length; i++){
				System.out.print(x[i]+",");
			}
			System.out.println();
		});
	}
	
	//找到当前数组基于排序的下一个数组。
	public static int[] getNextByOrder(int[] nums){
		int pivot = -1;
    	int change = 0;
    	//定位pivot
    	for(int i = nums.length-1; i > 0; i--){
    		if(nums[i] > nums[i-1]){
    			pivot = i-1;
    			break;
    		}
    	}
    	
    	//没有定位到pivot的位置，代表此时的数组已经是完全倒叙排列了。
    	if(pivot == -1){
    		return null;
    	}
    	//定位change
    	for(int i = nums.length-1; i >= 0; i--){
    		if(nums[i] > nums[pivot]){
    			change = i;
    			break;
    		}
    	}
    	
    	//交换pivot和change的元素位置
    	int tmp = nums[pivot];
    	nums[pivot] = nums[change];
    	nums[change] = tmp;
    	
    	//将pivot之后的元素的位置进行反转。
    	for(int i = pivot+1; i < nums.length; i++){
    		if(i < nums.length - i){
	    		tmp = nums[i];
	    		nums[i] = nums[nums.length - i];
	    		nums[nums.length - i] = tmp;
    		}else{
    			break;
    		}
    	}
    	
    	return nums;
	}
	
	//将数组进行插入排序。
    public static int[] bubbleSort(int[] nums){
    	for(int i = 0; i < nums.length-1; i++){
    		//如果当前元素比下一个元素大，交换这两个元素的位置。
    		//交换后，继续向前比较。
    		if(nums[i] > nums[i+1]){
    			int tmp = nums[i];
    			nums[i] = nums[i+1];
    			nums[i+1] = tmp;
    			
    			for(int j = i; j > 0; j--){
    				//如果当前元素比前一个元素还小，执行交换
    				if(nums[j] < nums[j-1]){
    					int tmp1 = nums[j];
    					nums[j] = nums[j-1];
    					nums[j-1] = tmp1;
    				}
    			}
    		}
    	}
    	
    	return nums;
    }
}
