package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.FDList;
import edu.buffalo.cse605.list.coarse.FDListCoarse;
import edu.buffalo.cse605.list.fine.FDListFine;
import edu.buffalo.cse605.list.rw.FDListRW;
import edu.buffalo.cse605.list.customrw.FDListCRW;
import edu.buffalo.cse605.test.Delete;
import edu.buffalo.cse605.test.insertLeft;
import edu.buffalo.cse605.test.insertLeftCoarse;
import edu.buffalo.cse605.test.insertRight;
import edu.buffalo.cse605.test.insertRightCoarse;
import edu.buffalo.cse605.test.readNext;
import edu.buffalo.cse605.test.readPrev;

public class Main {
	public static void main(String[] args) {
		// Constants
		int mb = 1024*1024;
		long ttime = 0; // total time
        Runtime runtime = Runtime.getRuntime();
		
		String scheme = args[0]; // list scheme
		int nt = Integer.parseInt(args[1]); // Threads
		int it =  Integer.parseInt(args[2]); // iterations
		int els =  Integer.parseInt(args[3]); // elements / number of threads
		int test = Integer.parseInt(args[4]); // testschemes
		
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
			f = new FDList<String> ("hi");
		}
		
		System.out.println("=========== Test => " + test + "; Scheme => " + scheme + "; Threads => " + nt + "; Els => " + els + " =========");
		els = els/(nt/2);
		switch(test) {
		case 1:
			// Workload 1
			// 64 threads
			// 64 writes
			els = els/2;
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

			break;
			
		case 2:
			// Workload 3
			// 64 threads
			// 32 to read
			// 32 write
			nt = nt/2;
			
			for (int i = nt; i < nt*2; i++) {
				if ( i % 2 == 0 ) {
					new Thread(new readNext(f.reader( f.head() ))).start();
				} else {
					new Thread(new readPrev(f.reader( f.head() ))).start();
				}
			}
			
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
				
			break;
			
		case 3:
			// Workload 3
			// 64 threads
			// 32 to read
			// 16 write
			// 16 delete
			nt = nt/4;
			
			for (int i = nt*2; i < nt*4; i++) {
				if ( i % 2 == 0 ) {
					new Thread(new readNext(f.reader( f.head() ))).start();
				} else {
					new Thread(new readPrev(f.reader( f.head() ))).start();
				}
			}
			
			for (int i = 0; i < it; i++) {
				startTime = System.currentTimeMillis();
				Thread[] threads = new Thread[nt*2];
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
				for (int j = nt; j < nt*2 ; j++) {
					c = f.reader( f.head() );
					threads[j] = new Thread(new Delete(c, els));
					threads[j].start();
				}
				for (Thread thread:threads) {
					try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				endTime   = System.currentTimeMillis();
				totalTime = endTime - startTime;
				ttime += totalTime;
			}
			
			break;
			
		case 4:
			// Workload 4
			// only 32 and 64
			// 64 threads
			// 48 to read
			// 12 write
			// 4 delete
			nt = nt/4;
			
			for (int i = nt; i < nt*4; i++) {
				if ( i % 2 == 0 ) {
					new Thread(new readNext(f.reader( f.head() ))).start();
				} else {
					new Thread(new readPrev(f.reader( f.head() ))).start();
				}
			}
			
			for (int i = 0; i < it; i++) {
				startTime = System.currentTimeMillis();
				Thread[] threads = new Thread[nt];
				for (int j = 0; j < (nt - nt/2) ; j+=2) {
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
				for (int j = (nt - nt/2); j < nt ; j++) {
					c = f.reader( f.head() );
					threads[j] = new Thread(new Delete(c, els));
					threads[j].start();
				}
				for (Thread thread:threads) {
					try {
						if ( thread == null ) {
							thread.join();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				endTime   = System.currentTimeMillis();
				totalTime = endTime - startTime;
				ttime += totalTime;
			}
			break;
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
//		System.out.println("Total List count => " + m + " => " + n + " => " + f.count);
		
        System.out.println("Used Memory => " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
//        System.out.println("Free Memory => " + runtime.freeMemory() / mb);
//        System.out.println("Total Memory => " + runtime.totalMemory() / mb);
//        System.out.println("Max Memory => " + runtime.maxMemory() / mb);
        
        System.exit(1);

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
