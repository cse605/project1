package edu.buffalo.cse605.list.customrw;

import edu.buffalo.cse605.list.FDList;


public class FDListCRW<T> extends FDList<T> {
	
	public FDListCRW(T val) {
		super(val);
		this.head = new ElementCRW<T>(val);
	}
	
	@Override
	public ElementCRW<T> head() {
		return (ElementCRW<T>) this.head;
	}
	
	
	public CursorCRW<T> reader(ElementCRW<T> start) {
		return new CursorCRW<T>(start);
	}
}
