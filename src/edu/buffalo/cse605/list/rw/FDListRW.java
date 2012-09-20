package edu.buffalo.cse605.list.rw;

import edu.buffalo.cse605.list.FDList;


public class FDListRW<T> extends FDList<T> {
	
	public FDListRW(T val) {
		super(val);
		this.head = new ElementRW<T>(val);
	}
	
	@Override
	public ElementRW<T> head() {
		return (ElementRW<T>) this.head;
	}
	
	
	public CursorRW<T> reader(ElementRW<T> start) {
		return new CursorRW<T>(start, this);
	}
}
