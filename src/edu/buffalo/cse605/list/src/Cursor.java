package edu.buffalo.cse605.list.src;

import edu.buffalo.cse605.list.iface.ICursor;

public class Cursor<T> implements ICursor<T> {
	private Element<T> curr;
	private Writer<T> writer = new Writer<T>(this);
	
	public Cursor(Element<T> start) {
		this.curr = start; // Find out: Does it reference or assign ?
	}
	
	@Override
	public Element<T> curr() {
		return this.curr;
	}

	@Override
	public void next() {
		this.curr = curr.next();
	}

	@Override
	public void prev() {
		this.curr = curr.prev();
	}

	@Override
	public Writer<T> writer() {
		return writer;
	}

}
