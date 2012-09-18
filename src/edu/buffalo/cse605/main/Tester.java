package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.FDList;
import edu.buffalo.cse605.list.coarse.CursorCoarse;
import edu.buffalo.cse605.list.coarse.FDListCoarse;

public class Tester implements Runnable {
	FDListCoarse<String> list;
	CursorCoarse<String> c;
	int id;
	
	public Tester(FDListCoarse<String> list, int id) {
		this.list = list;
		this.id = id;
	}
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		
//		synchronized(list) {
			c = this.list.reader( this.list.head() );
			c.prev();
			for ( int i = 0; i < 1250; i++ ) {
				c.writer().delete();
			}
//		}
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Thread-" + this.id + " running time => " + totalTime);
	}
	
}
