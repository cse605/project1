package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.src.Cursor;
import edu.buffalo.cse605.list.src.FDList;

public class Tester implements Runnable {
	FDList<String> list;
	Cursor<String> c;
	int id;
	
	public Tester(FDList<String> list, int id) {
		this.list = list;
		this.id = id;
	}
	
	@Override
	public void run() {
		c = this.list.reader( this.list.head() );
		long startTime = System.currentTimeMillis();
		for ( int i = 0; i < 100000; i++ ) {
			synchronized(list) {
				c.writer().insertAfter(i + "");
				c.writer().delete();
			}
		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Thread-" + this.id + " running time => " + totalTime);
	}
	
}
