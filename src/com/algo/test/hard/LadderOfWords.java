package com.algo.test.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 单词接龙。给定start和end两个单词，找出所有从start到end之间的
 * 可能的最短转换序列。要求：
 * 1. 每次单词转换只能改变一个字母(所以，转换前的单词长度跟转换后应该是一样的，不涉及复杂的编辑距离)。
 * 2. 变换中出现的单词必须在字典中出现。
 * 
 * 思路：从start开始计算，其只变化一个字母可能变成的单词包括哪些。
 * 然后递归计算，直到结果中包含了end。
 * 
 * 样例
 * 给出数据如下：
 * 
 * start = "hit"
 * 
 * end = "cog"
 * 
 * dict = ["hot","dot","dog","lot","log"]
 * 
 * 返回
 * 
 * [
 * 
 *  ["hit","hot","dot","dog","cog"],
 * 
 *  ["hit","hot","lot","log","cog"]
 * 
 * ]
 * 
 * http://www.lintcode.com/zh-cn/problem/word-ladder-ii/
 * @author guozhen@proudsmart.com
 *
 */
public class LadderOfWords {
	
	public static class Node{
		private Node from;
		private String content;
		private Set<Node> possibleNext;
		public Node getFrom() {
			return from;
		}
		public void setFrom(Node from) {
			this.from = from;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Set<Node> getPossibleNext() {
			return possibleNext;
		}
		public void setPossibleNext(Set<Node> possibleNext) {
			this.possibleNext = possibleNext;
		}
		
		public void setPossibleNextContents(Set<String> possibleNextContents){
			this.possibleNext = possibleNextContents.stream().map(x -> {
				Node n = new Node(x);
				n.setFrom(this);
				return n;
			}).collect(Collectors.toSet());
		}
		
		public Node(){}
		public Node(String content){
			this.content = content;
		}
	}
	
	/**
	 * 查看当前Node的下一步转换可能包含的情况。
	 * 如果下一步的节点中包含end，代表此次转换已经找到了最短的转换路径。将最终节点返回回来，通过该节点即可获取整个路径。
	 * 如果下一步节点中不包含end，将当前节点的下一步转换的所有可能节点都塞入到Node中。返回null代表该节点无法通过一次转换得到end，
	 * 还需要获取其possibleNext继续查找。
	 * 
	 * @param node
	 * @param dictionary
	 * @param end
	 * @return
	 */
	private static Set<Node> findEndOfNode(String start, Set<String> dictionary, String end){
		dictionary.add(end);
		
		Set<String> visited = new HashSet<String>();
		Queue<Node> queue = new LinkedList<Node>();
		Set<Node> resultNodes = new HashSet<Node>();
		boolean findFlag = false;
		Node startNode = new Node(start);
		queue.offer(startNode);
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				Node currNode = queue.poll();
				String currWord = currNode.getContent();
				visited.add(currWord);
				Set<String> possibleNext = getPossibleNextWords(currWord, dictionary).stream().filter(x -> !visited.contains(x)).collect(Collectors.toSet());
				if(possibleNext.contains(end)){
					findFlag = true;
					Node n = new Node(end);
					n.setFrom(currNode);
					resultNodes.add(n);
				}else{
					Set<Node> possibleNextNodes = possibleNext.stream().map(x -> {
						Node node = new Node(x);
						node.setFrom(currNode);
						return node;
					}).collect(Collectors.toSet());
					possibleNextNodes.forEach(x -> queue.offer(x));
				}
			}
			
			if(findFlag){
				return resultNodes;
			}
		}
		
		return null;
	}
	
	private static Set<List<String>> getPossibleWays(String start, String end, final Set<String> dictionary){
		Set<Node> nodeSet = findEndOfNode(start, dictionary, end);
		Set<List<String>> ways = nodeSet.stream().map(x -> getWayByNode(x)).collect(Collectors.toSet());
		return ways;
	}
	
	public static void main(String[] args){
		String start = "hit";
		String end = "cog";
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		
		Set<List<String>> ways = getPossibleWays(start, end, dict);
		ways.forEach(x -> {
			x.forEach(y -> System.out.print(y+", "));
			System.out.println("-------------------");
		});
	}
	
	public static List<String> getWayByNode(Node node){
		List<String> list = new ArrayList<String>();
		while(node != null){
			list.add(0, node.content);
			node = node.getFrom();
		}
		
		return list;
	}
	

	public static Set<String> getPossibleNextWords(String word, Set<String> dictionary){
		Set<String> set = new HashSet<String>();
		for(String w : dictionary){
			if(isOneCharDiff(word, w)){
				set.add(w);
			}
		}
		return set;
	}

	/**
	 * 是否word和w只差一个字母。
	 * 只有在这两个单词长度必须一样，而且有且只有一个字母
	 * 不一样的情况下才能返回true，代表word到w之间的转换只需要改动一个字母。
	 * @param word
	 * @param w
	 * @return
	 */
	private static boolean isOneCharDiff(String word, String w) {
		if(word.length() != w.length()){
			return false;
		}
		
		int diffCount = 0;
		for(int i = 0; i < word.length(); i++){
			if(word.charAt(i) != w.charAt(i)){
				diffCount++;
			}
		}
		if(diffCount == 1){
			return true;
		}
		return false;
	}
}
