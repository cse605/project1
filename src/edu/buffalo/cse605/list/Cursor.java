package edu.buffalo.cse605.list;

import edu.buffalo.cse605.list.iface.ICursor;

public class Cursor<T> implements ICursor<T> {
	protected Element<T> curr;
	
	public FDList<T> list;
	
	public Cursor(Element<T> start) {
		this.curr = start;
	}
	
	public Element<T> curr(Element<T> curr) {
		this.curr = curr;
		return curr;
	}
	
	@Override
	public Element<T> curr() {
		return this.curr;
	}
	
	public Element<T> getnext(){
		return this.curr.next();
	}
	
	public Element<T> getprev(){
		return this.curr().prev();
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
		return new Writer<T>(this);
	}

}
