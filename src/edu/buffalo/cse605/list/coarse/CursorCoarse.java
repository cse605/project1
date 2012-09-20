package edu.buffalo.cse605.list.coarse;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.Element;
import edu.buffalo.cse605.list.FDList;
import edu.buffalo.cse605.list.iface.ICursor;

public class CursorCoarse<T> extends Cursor<T> {
	private WriterCoarse<T> writer = new WriterCoarse<T>(this);
	
	public CursorCoarse(Element<T> start, FDListCoarse<T> list) {
		super(start);
		this.list = list;
	}
	
	@Override
	public WriterCoarse<T> writer() {
		return writer;
	}

}
