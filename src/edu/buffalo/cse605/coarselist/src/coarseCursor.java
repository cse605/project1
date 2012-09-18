package edu.buffalo.cse605.coarselist.src;


import edu.buffalo.cse605.list.src.*;

public class coarseCursor<T> extends Cursor<T>{
	private Element<T> curr;
	private coarseWriter<T> writer = new coarseWriter<T>(this);
	public coarseCursor(Element<T> start) {	
		super(start); // Find out: Does it reference or assign ?
	}
	
	// TODO: needs to be not exposed to public
			
	
	public coarseWriter<T> cwriter() {
		return writer;
	}

}
