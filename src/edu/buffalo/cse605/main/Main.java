package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.src.Cursor;
import edu.buffalo.cse605.list.src.FDList;

public class Main {
	public static void main(String[] args) {
		FDList<String> f = new FDList<String> ("hi");
		Cursor<String> c;
		// Threading start
		c = f.reader( f.head() );
		
//		for ( int i = 0; i < 64; i++ ) {
//			new Thread(new Tester(f, i)).start();
//		}
		
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.next();
		System.out.println("head in list..." + f.head().value());
		// # Test2
		// Check whether list is circular
		if (f.head() == c.curr()) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		// # Test 3
		// Insert new element AFTER
		c.writer().insertAfter("1a");
		c.writer().insertAfter("2a");
		c.writer().insertAfter("3a");
		c.writer().insertAfter("4a");
		// # Validate Test 3;
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.prev();
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.prev();
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.prev();
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.prev();
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		// # Test 4
		// Insert new element BEFORE
		c.writer().insertBefore("b4");
		c.writer().insertBefore("b3");
		c.writer().insertBefore("b2");
		c.writer().insertBefore("b1");
		// # Validate Test 3;
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.next();
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.next();
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.next();
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.next();
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		
		c.writer().delete();
		c.writer().delete();
		c.writer().delete();
		c.writer().delete();
		c.writer().delete();
		c.writer().delete();
		c.writer().delete();
		c.writer().delete();
		c.writer().delete();
		
	}

}
