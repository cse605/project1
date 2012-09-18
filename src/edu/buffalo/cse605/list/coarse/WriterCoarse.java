package edu.buffalo.cse605.list.coarse;

import edu.buffalo.cse605.list.Writer;
import edu.buffalo.cse605.list.iface.IWriter;

public class WriterCoarse<T> extends Writer<T> implements IWriter<T> {	
	public WriterCoarse(CursorCoarse<T> cursor) {
		super(cursor); 
	}

	@Override
	public boolean insertBefore(T val) {
		this.cursor.list.count.getAndIncrement();
		return super.insertBefore(val);
	}

	@Override
	public boolean insertAfter(T val) {
		this.cursor.list.count.getAndIncrement();
		return super.insertAfter(val);
	}

	@Override
	public boolean delete() {
		this.cursor.list.count.getAndDecrement();
		return super.delete();
	}

}
