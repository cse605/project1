package edu.buffalo.cse605.list.coarse;

import edu.buffalo.cse605.list.Element;
import edu.buffalo.cse605.list.FDList;

public class FDListCoarse<T> extends FDList<T> {
	public FDListCoarse(T val) {
		super(val);
	}

	public CursorCoarse<T> reader(Element<T> start) {
		return new CursorCoarse<T>(start, this);
	}
}
