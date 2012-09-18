package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.FDList;
import edu.buffalo.cse605.list.coarse.CursorCoarse;
import edu.buffalo.cse605.list.coarse.FDListCoarse;
import edu.buffalo.cse605.list.fine.FDListFine;
import edu.buffalo.cse605.list.fine.testThread;
import edu.buffalo.cse605.list.fine.testThread2;

public class Main {
	public static void main(String[] args) {
		FDListFine<String> f;		
		f = new FDListFine<String> ("hi");	
		testThread test1=new testThread(f);
		testThread2 test2=new testThread2(f);
//		testThread2 test3=new testThread2(f);
		test1.start();
		test2.start();
//		test3.start();
	
//		FDListCoarse<String> f = new FDListCoarse<String> ("hi");
//		Thread[] threads = new Thread[8];
//		CursorCoarse<String> c;
//		c = f.reader( f.head() );
//		
//		for ( int i = 0; i < 100000; i++ ) {
//			c.writer().insertBefore(i+"");
//		}
//		c.prev();
//		
//		long startTime = System.currentTimeMillis();
//		
//		for (int i = 0; i < threads.length; i++) {
//		    threads[i] = new Thread(new Tester(f, i));
//		    threads[i].start();
//		}
//
//		for (Thread thread : threads) {
//			try {
//				thread.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println("count => " + f.count.get());
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		
//		if (f.head() == c.curr()) {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}
//		
//		long endTime   = System.currentTimeMillis();
//		long totalTime = endTime - startTime;
//		System.out.println("Total running time => " + totalTime);
		
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.next();
//		System.out.println("head in list..." + f.head().value());
//		
//		// #Test 1
//		c.writer().insertAfter("after");
//		c.writer().delete();
//		c.writer().insertBefore("before");
//		c.writer().delete();
//		
//		// # Test2
//		// Check whether list is circular
//		if (f.head() == c.curr()) {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}
//
//		// # Test 3
//		// Insert new element AFTER
//		c.writer().insertAfter("1a");
//		c.writer().insertAfter("2a");
//		c.writer().insertAfter("3a");
//		c.writer().insertAfter("4a");
//		// # Validate Test 3;
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.prev();
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.prev();
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.prev();
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.prev();
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		// # Test 4
//		// Insert new element BEFORE
//		c.writer().insertBefore("b4");
//		c.writer().insertBefore("b3");
//		c.writer().insertBefore("b2");
//		c.writer().insertBefore("b1");
//		// # Validate Test 3;
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.next();
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.next();
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.next();
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.next();
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		// # Test 5
//		// Delete all keys
//		c.writer().delete();
//		c.writer().delete();
//		c.writer().delete();
//		c.writer().delete();
//		c.writer().delete();
//		c.writer().delete();
//		c.writer().delete();
//		c.writer().delete();
//		c.writer().delete();
	}

}
