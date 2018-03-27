package com.algo.test.concurrent;


public class ThreadLocalTest {
	ThreadLocal<String> stringLocal = new ThreadLocal<String>(){
		@Override
		public String initialValue(){
			Thread t = Thread.currentThread();
			String name = t.getName();
			System.out.println("====Thread "+t+" is initialize string value, name is:" + name);
			return name;
		}
	};
	
	public String getString(){
		return stringLocal.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
		ThreadLocalTest t = new ThreadLocalTest();
		System.out.println(t.getString());
		
		Thread t1 = new Thread(){
			public void run(){
				System.out.println("----Thread "+Thread.currentThread()+" is initialize string value, name is:" + Thread.currentThread());
				System.out.println(t.getString());
			};
		};
		
		t1.start();
		t1.join();
		
		System.out.println(t.getString());
	}
}
