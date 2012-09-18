package edu.buffalo.cse605.list.fine;


public class FDListFine<T> {
	private ElementFine<T> head;
	private CursorFine<T> cursor;
	
	public FDListFine(T val) {
		this.head = new ElementFine<T>(val, null, null);
	}

	public ElementFine<T> head() {
		return this.head;
	}
	
	public CursorFine<T> freader(ElementFine<T> start) {
		cursor = new CursorFine<T>(start);
		return this.cursor;
	}
}
