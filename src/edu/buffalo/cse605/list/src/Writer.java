package edu.buffalo.cse605.list.src;

import edu.buffalo.cse605.list.iface.IWriter;

public class Writer<T> implements IWriter<T> {
	private Cursor<T> cursor; 
	
	public Writer(Cursor<T> cursor) {
		this.cursor = cursor; 
	}

	@Override
	public boolean insertBefore(T val) {
		System.out.println("insert before");
		Element<T> e = new Element<T>(val);
		cursor.curr().addBefore(e);
		cursor.prev();
		return false;
	}

	@Override
	public boolean insertAfter(T val) {
		System.out.println("insert after");
		Element<T> e = new Element<T>(val);
		cursor.curr().addAfter(e);
		cursor.next();
		return true;
	}

	@Override
	public boolean delete() {
		System.out.println("delete");
		// TODO Auto-generated method stub
		return false;
	}

}
