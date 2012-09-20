package edu.buffalo.cse605.list.fine;

import java.util.concurrent.locks.ReentrantLock;

import edu.buffalo.cse605.list.Element;

public class ElementFine<T> extends Element<T> {

	public ElementFine(T val) {
		super(val);
		this.nextlock = new ReentrantLock();
		this.prevlock = new ReentrantLock();
	}

}
