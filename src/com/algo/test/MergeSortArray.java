package com.algo.test;
/**
 * 合并排序数组的算法。
 * 给定两个已经排序的数组，将这两个数组合并成一个新数组，要求新数组中仍然是排序好的。
 * @author guozhen@proudsmart.com
 *
 */
public class MergeSortArray {
	/*
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public static int[] mergeSortedArray(int[] a, int[] b) {
        
        int[] result = new int[a.length+b.length];
        int x = 0, y = 0;
        int index = 0;
        while(x <= a.length && y <= b.length){
        	//a已经遍历完成，将b的剩余元素append到result中。
        	if(x == a.length){
        		while(y < b.length){
        			result[index++] = b[y++];
        		}
        		break;
        	}else if(y == a.length){
        		//反之，如果b已经遍历完成，将a的剩余元素append到result中。
        		while(x < b.length){
        			result[index++] = a[x++];
        		}
        		break;
        	}
        	//挑选a的下一个元素和b的下一个元素中较小的那个，append到result中。
        	//选中的元素所在是数组的index进行+1，不论哪个数组的值被选出来，
        	//数组中的值都需要+1
            int min = a[x];
            if(min < b[y]){
                x++;
            }else{
                min = b[y];
                y++;
            }
            result[index] = min;
            index++;
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		int[] x1 = new int[]{1,2,3,4};
		int[] x2 = new int[]{2,4,5,6};
		
		int[] result = mergeSortedArray(x1, x2);
		for(int i = 0; i < result.length; i++){
			System.out.print(result[i]+" ,");
		}
		
	}
}
