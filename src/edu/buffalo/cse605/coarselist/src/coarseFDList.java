package edu.buffalo.cse605.coarselist.src;


import edu.buffalo.cse605.list.src.*;
public class coarseFDList<T> extends FDList<T>{
	private Element<T> head;
	private coarseCursor<T> cursor;
	//private int count = 0;
	
	public coarseFDList(T val) {
		super(val);
	}
	
	public coarseCursor<T> creader(Element<T> start) {
		if (cursor == null) {
			cursor = new coarseCursor<T>(start);
		}
		// TODO: Logical Fallacy, make sure I can call reader multiple times
		return this.cursor;
	}
}
