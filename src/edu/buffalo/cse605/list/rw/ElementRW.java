package edu.buffalo.cse605.list.rw;

import java.util.concurrent.locks.ReentrantLock;

import edu.buffalo.cse605.list.Element;

public class ElementRW<T> extends Element<T> {

	public ElementRW(T val) {
		super(val);
		this.nextlock = new ReentrantLock();
		this.prevlock = new ReentrantLock();
	}

}
