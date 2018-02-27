package com.algo.test.hard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Least Frequency Used（最不经常使用）。
 * 
 * 给定一个capacity，实现LFU的缓存算法。需要有get和set方法。
 * 
 * http://www.lintcode.com/zh-cn/problem/lfu-cache/
 * @author guozhen@proudsmart.com
 *
 */
public class LFUCache<K, V> extends HashMap<K, V> {
	private Map<K, HitRate> hitRate = new HashMap<K, HitRate>();
	private int maxCacheSize = 8;
	
	//命中次数多，或者最新命中时间长的，设置为比较大的。
	class HitRate implements Comparable<HitRate>{
		
		K k;
		int hitCount;
		long lastTime;
		
		public HitRate(){}
		public HitRate(K k, int hitCount, long lastTime){
			this.k = k;
			this.hitCount = hitCount;
			this.lastTime = lastTime;
		}
		
		@Override
		public int compareTo(HitRate o) {
			if(this.hitCount > o.hitCount){
				return 1;
			}else if(this.hitCount < o.hitCount){
				return -1;
			}else{
				if(this.lastTime > o.lastTime){
					return 1;
				}else{
					return -1;
				}
			}
		}
		
		public void increHitCount(){
			this.hitCount++;
		}
	}
	
	
	public void set(K key, V value){
		if(hitRate.size() >= maxCacheSize){
			K k = getLFUKey();
			if(k != null){
				hitRate.remove(k);
				this.remove(k);
			}
		}
		super.put(key, value);
		hitRate.put(key, new HitRate(key, 1, System.currentTimeMillis()));
	}
	
	@Override
	public V get(Object key){
		V v = super.get(key);
		if(v != null){
			HitRate rate = hitRate.get(key);
			rate.hitCount++;
			rate.lastTime = System.currentTimeMillis();
		}
		return v;
	}
	
	private K getLFUKey(){
		HitRate min = Collections.min(hitRate.values());
		return min.k;
	}
	
	
}
