package com.algo.testcopy;

import java.util.ArrayList;
import java.util.List;

/**
 * 参照DistinctArrangeOfArray
 * @author guozhen@proudsmart.com
 *
 */
public class PremuteUnique {
	/**
	 * 选择排序思路是：
	 * 从第一个元素开始，找到最小元素，跟第一个元素交换；
	 * 从第二个元素开始，找到足校元素，跟第二个元素交换；
	 * ......
	 * 直到最后一个元素。
	 * [1, 2, 3, 4, 2, 1]
	 * @param nums
	 * @return
	 */
	public static int[] selectSort(int[] nums){
		int minIndex = 0;
		for(int i = 0; i < nums.length; i++){
			minIndex = i;
			for(int j = i; j < nums.length; j++){
				if(nums[minIndex] > nums[j]){
					minIndex = j;
				}
			}
			switchElement(nums, i, minIndex);
		}
		
		return nums;
	}
	
	/**
	 * 插入排序。思路是：
	 * 从第二个元素开始，跟前面的元素依次比较，如果小于前面的元素，就跟前面的元素进行替换，形成局部有序。
	 * 从第三个元素开始，继续跟前面的元素依次比较。
	 * .....
	 * 
	 * 要注意插入排序跟冒泡排序的区别：
	 * 插入排序是形成局部有序，冒泡排序是每次找到一个最值。
	 * @param nums
	 * @return
	 */
//	public static int[] insertSort(int[] nums){
//		for(int i = 1; i < nums.length; i++){
//			for(int j = i; j > 0; j--){
//				if(nums[j] < nums[i]){
//					switchElement(nums, i, j);
//				}
//			}
//		}
//	}
	
	public static void switchElement(int[] nums, int i, int j){
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,2,1};
//		nums = selectSort(nums);
		List<int[]> list = premuteUnique(nums);
		list.forEach(x -> {
			for(int i = 0; i < x.length; i++){
				System.out.print(x[i]+",");
			}
			System.out.println();
		});
	}
	
	public static List<int[]> premuteUnique(int[] nums){
		nums = selectSort(nums);
		List<int[]> list = new ArrayList<int[]>();
		list.add(nums.clone());
		
		while((nums = getNextOrderedNum(nums)) != null){
			list.add(nums.clone());
		}
		
		return list;
	}
	
	/**
	 * 将一个数组看成有序数组，获取当前有序数组的下一个数组。
	 * 例如:[1,2,3]对应的下一个有序数组应该是：[1,3,2]
	 * 计算方法：
	 * 1. 从数组最后开始，依次进行相邻元素的比较，找到第一个不是递增的数字，设置为pivot（在以上例子中，从3到2不是递增的，cursor设置为2所在的数组index）。
	 *    如果找不到，则该数组已经是完全逆向排序的数组了。
	 * 2. 从数组的最后开始，将每个数字跟pivot进行比较，找到第一个大于该数字，记录下来保存为change。（在这个例子中，应该是3）
	 * 3. 将change跟pivot进行交换。
	 * 4. 将pivot之后的元素进行对调。对调之后的数组既是下一个有序数组。
	 * @param nums
	 * @return
	 */
	private static int[] getNextOrderedNum(int[] nums) {
		//寻找pivot：
		int pivot = -1;
		for(int i = nums.length - 1; i > 0; i--){
			if(nums[i] > nums[i-1]){
				pivot = i-1;
				break;
			}
		}
		
		//没找到pivot，代表逆向排序已经完成了。
		if(pivot == -1){
			return null;
		}
		
		//从最后一个元素开始，跟pivot比较，找到第一个大于pivot的数字。
		int change = -1;
		for(int i = nums.length - 1; i > 0; i--){
			if(nums[i] > nums[pivot]){
				change = i;
				break;
			}
		}
		
		//交换pivot和change
		switchElement(nums, pivot, change);
		
		//从pivot开始，将该数组后边的元素进行逆向反转。
		for(int i = pivot + 1; i < nums.length; i++){
			if(i < nums.length - i){
				switchElement(nums, i, nums.length - i + pivot);
			}
		}
		return nums;
	}
}
