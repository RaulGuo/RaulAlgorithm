package com.algo.test.hard.dp;

/**
 * 使用动态规划的方法求两个单词的编辑距离。
 * 动态规划来解决这个问题的思路是：
 * 假设两个字符串为s1和s2。他们的长度分别为len(s1)和len(s2)。他们的编辑距离为e[len(s1)][len(s2)]
 * 则可能有以下几种情况：
 * 1. 如果s1和s2的最后一个字符相同，则e[len(s1)][len(s2)]=e[len(s1)-1][len(s2)-1]
 * 2. 如果s1和s2的最后一个字符不相同，有以下处理方式：
 * (1) 修改s1或者s2的最后一个字符，使其相同；此时，e[len(s1)][len(s2)] = e[len(s1)-1][len(s2)-1]+1
 * (2) 在s1的末尾插入s2末尾的字符，则此时e[len(s1)][len(s2)] = e[len(s1)][len(s2)-1]+1
 * (3) 在s2的末尾插入s1末尾的字符，则此时e[len(s1)][len(s2)] = e[len(s1)-1][len(s2)]+1
 * (4) 删除s1末尾的字符，则此时e[len(s1)][len(s2)] = e[len(s1)-1][len(s2)]+1
 * (5) 删除s2末尾的字符，则此时e[len(s1)][len(s2)] = e[len(s1)][len(s2)-1]+1
 * 
 * 所以，如果s1和s2的最后一个字符不相同，则e[len(s1)][len(s2)]可能的值为min(e[len(s1)-1][len(s2)], e[len(s1)][len(s2)-1], e[len(s1)-1][len(s2)-1])+1
 * 
 * 这个问题可以用动态规划的思路来解决。
 * 这个问题中，状态就是s1中前若干个字符（从1到s1.length）到s2中前若干个字符（从1到s2.length）个字符的编辑距离。
 * 初始状态的值为：如果s1为空，则s1到s2中从1到n个字符组成的字符串的编辑距离就是n。对于s2也是同样的道理
 *    c o f f e e
 *  0 1 2 3 4 5 6
 *c 1 
 *a 2 
 *f 3 
 *e 4 
 *
 *初始化完成后，其状态转移方程为：
 *e[i][j] = if(s1[i] == s2[j]) e[i-1][j-1] else min(e[i-1][j], e[i][j-1], e[i-1][j-1])+1
 * 
 * @author guozhen@proudsmart.com
 *
 */
public class EditDistanceDP {

public static int getEditDistance(String s1, String s2){
	int[][] ed = new int[s1.length()+1][s2.length()+1];
	for(int i = 1; i < s2.length(); i++){
		ed[0][i] = i;
	}
	for(int i = 1; i < s1.length(); i++){
		ed[i][0] = i;
	}
	
	for(int i = 1; i <= s1.length(); i++){
		for(int j = 1; j <= s2.length(); j++){
			if(s1.charAt(i-1) == s2.charAt(j-1)){
				ed[i][j] = ed[i-1][j-1];
			}else{
				ed[i][j] = Math.min(ed[i-1][j-1], Math.min(ed[i-1][j], ed[i][j-1]))+1;
			}
		}
	}
	
	return ed[s1.length()][s2.length()];
}
	
	public static void main(String[] args) {
		String s1 = "coffee";
		String s2 = "cafe";
		System.out.println(getEditDistance(s1, s2));
	}
}
