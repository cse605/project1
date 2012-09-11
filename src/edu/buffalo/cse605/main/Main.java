package edu.buffalo.cse605.main;

import edu.buffalo.cse605.list.src.FDList;
import edu.buffalo.cse605.list.src.Cursor;

public class Main {
	public static void main(String[] args) {
		FDList<String> f;
		Cursor<String> c;
		
		f = new FDList<String> ("hi");
		c = f.reader( f.head() );
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
		c.writer().insertAfter("foo");
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.prev();
		System.out.println("prev current element pointed by cursor..." + c.curr().toString());
		c.next();
		c.next();
		System.out.println("next current element pointed by cursor..." + c.curr().toString());
		
		// # Test 4
		// Insert new element BEFORE
//		c.writer().insertBefore("bar");
//		System.out.println("current element pointed by cursor..." + c.curr().toString());
		
//		c.writer().insertBefore("hi");
//		c.writer().insertAfter("good");
	}

}
