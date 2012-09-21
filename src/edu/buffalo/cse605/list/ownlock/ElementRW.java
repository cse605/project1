package edu.buffalo.cse605.list.ownlock;

import edu.buffalo.cse605.lock.ReadWriteLock;
import edu.buffalo.cse605.list.*;

public class ElementRW<T> extends Element<T> {
	 public readwritelock rwnextlock;
	public  readwritelock rwprevlock;
	public ElementRW(T val) {
		super(val);
		rwnextlock = new readwritelock();
		rwprevlock = new readwritelock();
	}
}
