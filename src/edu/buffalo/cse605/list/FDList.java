package edu.buffalo.cse605.list;

import java.util.concurrent.atomic.AtomicInteger;

public class FDList<T> {
	protected Element<T> head;
	public AtomicInteger count = new AtomicInteger(0);
	
	public FDList(T val) {
		this.head = new Element<T>(val, null, null);
	}

	public Element<T> head() {
		return this.head;
	}
	
	public Cursor<T> reader(Element<T> start) {
		return new Cursor<T>(start);
	}
}
