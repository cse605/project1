package edu.buffalo.cse605.list.iface;

import edu.buffalo.cse605.list.src.Element;
import edu.buffalo.cse605.list.src.Writer;

public interface ICursor<T> {
	public Element<T> curr();
	public void next();
	public void prev();
	public Writer<T> writer();
}
