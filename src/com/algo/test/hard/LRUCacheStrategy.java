package com.algo.test.hard;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 设计一个LRU（最近最少使用）缓存策略的数据结构。支持的策略包括：
 * 获取数据和写入数据。
 * 获取数据时，如果缓存中存在这个key，则返回其数据值（一般都是正数）；否则返回-1
 * 写入数据时，如果key还没有缓存，则将其写入缓存中。当缓存达到上限，则写入之前删除最少使用的数据来腾出空闲位置。
 * 
 * @author guozhen@proudsmart.com
 *
 */
public class LRUCacheStrategy {
	private Map<String, Integer> map;
	
	public LRUCacheStrategy(){
		this(1024);
	}
	
	public LRUCacheStrategy(int capacity){
		map = new LinkedHashMap<String, Integer>(capacity, 0.75f, true){
			@Override
			protected boolean removeEldestEntry(Map.Entry<String, Integer> entry){
				if(this.size() >= capacity){
					return true;
				}
				return false;
			}
		};
	}
	
    public int get(String key) {
    	return map.get(key);
    }
    
    public Set<String> keySet(){
    	return map.keySet();
    }

    public void set(String key, int value) {
    	map.put(key, value);
    }
	
    public static void main(String[] args) {
		LRUCacheStrategy lru = new LRUCacheStrategy(16);
		for(int i = 1; i <= 100; i++){
			lru.set("hello"+i, i);
			if(i%10 == 0){
				lru.keySet().forEach(x -> {
					System.out.print(x+", ");
				});
				System.out.println("----------");
			}
		}
	}
}
