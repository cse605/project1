package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.src.FDList;
import edu.buffalo.cse605.list.src.Cursor;

public class Main {
	public static void main(String[] args) {
		FDList<String> f = new FDList<String> ("hi");
		// Threading start
//		c = f.reader( f.head() );
		for ( int i = 0; i < 64; i++ ) {
			new Thread(new Tester<String>(f, i)).start();
		}
		
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
//		c.next();
//		System.out.println("head in list..." + f.head().value());
//		// # Test2
//		// Check whether list is circular
//		if (f.head() == c.curr()) {
//			System.out.println("true");
//		} else {
//			System.out.println("false");
//		}

		// Time taken to insert 100*64 elements
		// spawn 64 threads
		// each thread runs loop 1-100 insertBefore and insertAfter
		
//		// # Test 3
//		// Insert new element AFTER
//		c.writer().insertAfter("1");
//		c.writer().insertAfter("2");
//		c.writer().insertAfter("3");
//		c.writer().insertAfter("4");
//		// # Test 4
//		// Insert new element BEFORE
//		c.writer().insertBefore("5");
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
	}

}
