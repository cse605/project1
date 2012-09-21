package edu.buffalo.cse605.list.rw;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.buffalo.cse605.list.Element;

public class ElementRW<T> extends Element<T> {

	public ElementRW(T val) {
		super(val);
		this.rnextlock = new ReentrantReadWriteLock().readLock();
		this.rprevlock = new ReentrantReadWriteLock().readLock();
		this.wprevlock = new ReentrantReadWriteLock().writeLock();
		this.wnextlock = new ReentrantReadWriteLock().writeLock();
	}

}
