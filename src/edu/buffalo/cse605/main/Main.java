package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.FDList;
import edu.buffalo.cse605.list.coarse.FDListCoarse;
import edu.buffalo.cse605.list.fine.FDListFine;
import edu.buffalo.cse605.list.rw.FDListRW;
import edu.buffalo.cse605.list.customrw.FDListCRW;
import edu.buffalo.cse605.test.insertLeft;
import edu.buffalo.cse605.test.insertLeftCoarse;
import edu.buffalo.cse605.test.insertRight;
import edu.buffalo.cse605.test.insertRightCoarse;

public class Main {
	public static void main(String[] args) {
		// Constants
		int mb = 1024*1024;
		long ttime = 0; // total time
        Runtime runtime = Runtime.getRuntime();
		
		String scheme = args[0]; // list scheme
		int nt = Integer.parseInt(args[1]); // Threads
		int it =  Integer.parseInt(args[2]); // iterations
		int els =  Integer.parseInt(args[3])/(nt/2); // elements / number of threads
//		String test = args[4]; // testschemes
		
		FDList<String> f;
		Cursor<String> c, temp;
		long startTime, endTime, totalTime;
		// Validation of elements inserted
		int m = 0, n = 0;
		
		if (scheme.equals("fine")) {
			f = new FDListFine<String> ("hi");
		} else if (scheme.equals("coarse")) {
			f = new FDListCoarse<String> ("hi");
		} else if (scheme.equals("rw")) {
			f = new FDListRW<String> ("hi");
		} else if (scheme.equals("crw")) {
			f = new FDListCRW<String> ("hi");
		} else {
			System.out.println("testing...");
			f = new FDList<String> ("hi");
		}
		
//		new Thread(new readNext(f.reader( f.head() ))).start();
//		new Thread(new readPrev(f.reader( f.head() ))).start();
		
		System.out.println("=========== Scheme => " + scheme + "; Threads => " + nt + "; Els => " + els + " =========");
		
		for (int i = 0; i < it; i++) {
			startTime = System.currentTimeMillis();
			Thread[] threads = new Thread[nt];
			for (int j = 0; j < nt ; j+=2) {
				c = f.reader( f.head() );
				if ( scheme.equals("coarse") ) {
					threads[j] = new Thread(new insertLeftCoarse(f, c, els));
					threads[j+1] = new Thread(new insertRightCoarse(f, c, els));
				} else {
					threads[j] = new Thread(new insertLeft(c, els));
					threads[j+1] = new Thread(new insertRight(c, els));
				}
				threads[j].start();
				threads[j+1].start();
			}
			for (int j = 0; j < nt; j++) {
				try {
					threads[j].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			endTime   = System.currentTimeMillis();
			totalTime = endTime - startTime;
			ttime += totalTime;
		}

		System.out.println("Total running time => " + ttime/it);
		
		/* temp cursor on the head for validation */
		temp = f.reader( f.head() );
		
		temp.next();
		while(temp.curr() != f.head()) {
			m++;
			temp.next();
		}
		temp.prev();
		while(temp.curr() != f.head()) {
			n++;
			temp.prev();
		}
		
		System.out.println("Total List count => " + m + " => " + n + " => " + f.count);
        System.out.println("==== Heap utilization statistics [MB] ====");
        System.out.println("Used Memory => " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Free Memory => " + runtime.freeMemory() / mb);
        System.out.println("Total Memory => " + runtime.totalMemory() / mb);
        System.out.println("Max Memory => " + runtime.maxMemory() / mb);

//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		
//		if (f.head() == c.curr()) {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}		
//		
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
//		
//		// deleted last element ?
//		if (c.curr() == null) {
//			System.out.println("no elements left");
//		} else {
//			System.out.println("elements left");
//		}
		
	}

}
