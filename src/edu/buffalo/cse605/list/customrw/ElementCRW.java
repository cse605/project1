package edu.buffalo.cse605.list.customrw;

import edu.buffalo.cse605.list.Element;
import edu.buffalo.cse605.lock.ReadWriteLock;

public class ElementCRW<T> extends Element<T> {

	public ElementCRW(T val) {
		super(val);
		this.rwcnextlock = new ReadWriteLock();
		this.rwcprevlock = new ReadWriteLock();
	}

}
