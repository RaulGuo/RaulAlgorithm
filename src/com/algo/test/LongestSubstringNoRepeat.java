package com.algo.test;

/**
 * 给定一个字符串，获取该字符串的最长的没有重复字幕的子串。
 * @author guozhen@proudsmart.com
 *
 */
public class LongestSubstringNoRepeat {
	
	/**
	 * 截取最长子串方法：
	 * 从第一个字符开始，挨个向后找；后边的字符如果跟已有的字符重复，则认为已经
	 * 截取的字符就是从当前位置开始的最长字符。
	 * 然后从下一个字符开始，继续向后找；找到从该字符开始后的最长字符，跟已有的最长字符比较，保留更长的。
	 * .....
	 */
	public static String getShortest(String content){
		String currMaxStr = null;
		int currMaxLength = 0;
		
		char[] arr = content.toCharArray();
		//从第一个字符开始，找当前字符所能到达的最长的无重复字符串。
		for(int i = 0 ; i < arr.length; i++){
			//从下一个元素开始，每取到一个新字符，就跟前面的所有字符进行比较。
			boolean duplicateCheck = false;
			for(int j = i+1; j < arr.length; j++){
				char tmp = arr[j];
				//将j下标的字符跟从i到j的下标字符比较。如果相同，则从i开始的字符串最远就到达这里了。
				for(int k = i; k < j; k++){
					if(arr[k] == tmp){
						if(currMaxLength < j-i){
							currMaxLength = j-i;
							currMaxStr = content.substring(i, j);
						}
						duplicateCheck = true;
						break;
					}
					
					//如果遍历到了最后，也没有找到重复元素，这时才会执行到这里
					if(k == j-1){
						if(currMaxLength < j - i + 1){
							currMaxLength = j - i + 1;
							currMaxStr = content.substring(i, content.length());
						}
					}
				}
				
				if(duplicateCheck){
					break;
				}
			}
			
		}
		
		return currMaxStr;
	}
	
	public static void main(String[] args) {
		String str = getShortest("pwwkew");
		System.out.println(str);
		
		str = getShortest("aaaab");
		System.out.println(str);
	}
}
