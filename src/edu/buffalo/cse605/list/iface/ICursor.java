package edu.buffalo.cse605.list.iface;

import edu.buffalo.cse605.list.Element;
import edu.buffalo.cse605.list.Writer;

public interface ICursor<T> {
	public Element<T> curr();
	public void next();
	public void prev();
	public Writer<T> writer();
}
