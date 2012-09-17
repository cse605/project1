package edu.buffalo.cse605.list.src;

import edu.buffalo.cse605.list.iface.ICursor;

public class Cursor<T> implements ICursor<T> {
	private Element<T> curr;
	private Writer<T> writer = new Writer<T>(this);
	
	public Cursor(Element<T> start) {
		this.curr = start;
	}
	
	// TODO: needs to be not exposed to public
	public Element<T> curr(Element<T> curr) {
		this.curr = curr;
		return curr;
	}
	
	@Override
	public Element<T> curr() {
		return this.curr;
	}

	@Override
	public void next() {
		 this.curr = curr.next();
	}
	
	public Element getnext(){
		return this.curr.next();
	}
	
	public Element getprev(){
		return this.curr().prev();
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
