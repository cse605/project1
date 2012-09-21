package edu.buffalo.cse605.list.ownlock;

import edu.buffalo.cse605.list.*;

public class FDListRW<T> extends FDList<T>{
	
	public FDListRW(T val) {
		super(val);
		this.head = new ElementRW<T>(val);
	}
	
	@Override
	public ElementRW<T> head() {
		return (ElementRW<T>) this.head;
	}
	
	@Override
	public CursorRW<T> reader(Element<T> start) {
		return new CursorRW<T>((ElementRW <T>) start);
	}
}
