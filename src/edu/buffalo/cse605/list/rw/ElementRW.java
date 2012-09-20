package edu.buffalo.cse605.list.rw;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.buffalo.cse605.list.Element;

public class ElementRW<T> extends Element<T> {

	public ElementRW(T val) {
		super(val);
		this.rwnextlock = new ReentrantReadWriteLock();
		this.rwprevlock = new ReentrantReadWriteLock();
	}

}
