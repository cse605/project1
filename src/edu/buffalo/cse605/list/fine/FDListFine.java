package edu.buffalo.cse605.list.fine;

import edu.buffalo.cse605.list.FDList;


public class FDListFine<T> extends FDList<T> {
	
	public FDListFine(T val) {
		super(val);
		this.head = new ElementFine<T>(val);
	}
	
	@Override
	public ElementFine<T> head() {
		return (ElementFine<T>) this.head;
	}
	
	
	public CursorFine<T> reader(ElementFine<T> start) {
		return new CursorFine<T>(start, this);
	}
}
