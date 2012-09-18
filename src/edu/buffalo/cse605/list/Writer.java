package edu.buffalo.cse605.list;

import edu.buffalo.cse605.list.iface.IWriter;

public class Writer<T> implements IWriter<T> {
	protected Cursor<T> cursor; 
	
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
//		synchronized (cursor.list) {
			if (cursor.curr() == null) { 
				throw new Error("the list is empty");
			} else if (cursor.getnext() == cursor.getprev() && cursor.getnext() == cursor.curr()) {
				cursor.curr(null);
			} else {
				cursor.curr().delete();
				cursor.prev();
			}
			return true;
//		}
	}

}
