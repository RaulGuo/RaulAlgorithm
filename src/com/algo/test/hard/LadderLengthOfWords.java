package com.algo.test.hard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 单词接龙。给定start和end两个单词，找出所有从start到end之间的
 * 可能的最短转换序列的长度。要求：
 * 1. 每次单词转换只能改变一个字母(所以，转换前的单词长度跟转换后应该是一样的，不涉及复杂的编辑距离)。
 * 2. 变换中出现的单词必须在字典中出现。
 */
public class LadderLengthOfWords {
	
	/**
	 * 获取从from到end所需要的转换的长度。
	 * 实现思路：从from开始，依次找到其下一步转换可能变换成的单词，如果找到了end，就返回长度，如果没有返回，则继续向下找，直到找到为止。
	 * 实现的关键：怎样实现逐层查找是否有匹配的end。
	 * 巧用Queue（LinkedList），在每一次循环开始时，记录下当前队列的长度，并对这些元素进行逐个poll（这里需要预先获取队列当前的长度，而不能在循环中去动态
	 * 的获取队列中的元素，因为在循环中需要向队列中塞入在下一层的查找中可能的访问元素，所以其长度在循环中是会变化的）。
	 * poll出来的元素查找其下一次转换是否能转换成end，如果可以，直接返回当前长度即可。否则，将其下一次可能转换成的元素添加到队列中。
	 * 添加到队列中（要注意，添加的这些元素不会影响到上一步的访问顺序）
	 * 
	 * @param from
	 * @param end
	 * @param dic
	 * @return
	 */
	public static int getLength(String from, String end, Set<String> dic){
		//已经访问过的单词
		Set<String> visitedWord = new HashSet<String>();
		LinkedList<String> queue = new LinkedList<String>();
		queue.offer(from);
		dic.add(end);
		visitedWord.add(from);
		int length = 1;
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				String currWord = queue.poll();
				Set<String> possibleNext = LadderOfWords.getPossibleNextWords(currWord, dic).stream().filter(x -> !visitedWord.contains(x)).collect(Collectors.toSet());
				if(possibleNext.contains(end)){
					return length+1;
				}else{
					possibleNext.forEach(x -> queue.offer(x));
				}
			}
			length++;
		}
		
		return -1;
	}
	
	
	
	
	
	
	
	
	
	public static int getLadderLength(String start, String end, Set<String> dict){
		List<String> queue = new ArrayList<String>();
		queue.add(start);
		int length = 0;
		dict.add(end);
		Set<String> visited = new HashSet<String>();
		visited.add(start);
		while(!queue.isEmpty()){
			int currSize = queue.size();
			for(int i = 0; i < currSize; i++){
				String currWord = queue.remove(0);
				Set<String> possibleNext = LadderOfWords.getPossibleNextWords(currWord, dict).stream().filter(x -> !visited.contains(x)).collect(Collectors.toSet());
				if(possibleNext.contains(end)){
					return length+1;
				}else{
					possibleNext.forEach(x -> {
						queue.add(x);	
					});
				}
				visited.add(currWord);
			}
			length++;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		String from = "hit";
		String end = "cog";
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		
		int length = getLength(from, end, dict);
		int length1 = getLadderLength(from, end, dict);
		System.out.println("length from "+from+" to end is:"+length);
		System.out.println("length from "+from+" to end is:"+length1);
	}
}
