package edu.buffalo.cse605.list.customrw;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.buffalo.cse605.list.Element;
import edu.buffalo.cse605.lock.ReadWriteLock;

public class ElementRW<T> extends Element<T> {

	public ElementRW(T val) {
		super(val);
		this.rwcnextlock = new ReadWriteLock();
		this.rwcprevlock = new ReadWriteLock();
	}

}
