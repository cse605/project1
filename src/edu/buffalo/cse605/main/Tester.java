package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.src.Cursor;
import edu.buffalo.cse605.list.src.FDList;

public class Tester<T> implements Runnable {
	FDList<T> list;
	Cursor<T> c;
	int id;
	
	public Tester(FDList<T> list, int id) {
		this.list = list;
		this.id = id;
	}
	
	@Override
	public void run() {
		c = this.list.reader( this.list.head() );
		long startTime = System.currentTimeMillis();
		for ( int i = 0; i < 100000; i++ ) {
//			System.out.println("Inserting ... => " + i + " for thread " + this.hashCode());
			c.writer().insertAfter((T) "1");
		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Thread-" + this.id + " running time => " + totalTime);
	}
	
}
