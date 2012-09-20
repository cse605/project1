package edu.buffalo.cse605.list.fine;

import edu.buffalo.cse605.list.Element;
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
	
	@Override
	public CursorFine<T> reader(Element<T> start) {
		return new CursorFine<T>((ElementFine<T>) start, this);
	}
}
