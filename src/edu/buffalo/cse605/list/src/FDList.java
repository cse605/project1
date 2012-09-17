package edu.buffalo.cse605.list.src;

public class FDList<T> {
	private Element<T> head;
	private int count = 0;
	
	public FDList(T val) {
		this.head = new Element<T>(val, null, null);
	}

	public Element<T> head() {
		return this.head;
	}
	
	public Cursor<T> reader(Element<T> start) {
//		if (cursor == null) {
//			cursor = new Cursor<T>(start);
//		}
		// TODO: Logical Fallacy, make sure I can call reader multiple times
		return new Cursor<T>(start);
	}
}
