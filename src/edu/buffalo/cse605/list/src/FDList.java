package edu.buffalo.cse605.list.src;

public class FDList<T> {
	private Element<T> head;
	public volatile int count = 0;
	
	public FDList(T val) {
		this.head = new Element<T>(val, null, null);
	}

	public Element<T> head() {
		return this.head;
	}
	
	public Cursor<T> reader(Element<T> start) {
		return new Cursor<T>(start);
	}
}
