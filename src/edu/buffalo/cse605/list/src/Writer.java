package edu.buffalo.cse605.list.src;

import edu.buffalo.cse605.list.iface.IWriter;

public class Writer<T> implements IWriter<T> {
	private Cursor<T> cursor; 
	
	public Writer(Cursor<T> cursor) {
		this.cursor = cursor; 
	}

	@Override
	public boolean insertBefore(T val) {
		Element<T> e = new Element<T>(val);
		if(cursor.curr() == null) {
			cursor.curr(e);
		} else{
			cursor.curr().addBefore(e);
			cursor.prev();
		}
		return true;
	}

	@Override
	public boolean insertAfter(T val) {
		Element<T> e = new Element<T>(val);
		if ( cursor.curr() == null ) {
			 cursor.curr(e);
			 
		} else {
			cursor.curr().addAfter(e);
			cursor.next();
		}
		return true;
	}

	@Override
	public boolean delete() {
		//System.out.println("delete");
		if (cursor.curr()==null) { 
			throw new Error("the list is empty");
		} else if (cursor.getnext() == cursor.getprev() && cursor.getnext() == cursor.curr()) {
		    System.out.println("delete "+cursor.curr().value()); //only one element in the list
			cursor.curr(null);
		} else {
			cursor.curr().delete();
			cursor.curr(cursor.curr().prev());
		}
		return true;
	}

}
