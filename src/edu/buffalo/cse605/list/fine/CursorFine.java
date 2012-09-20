package edu.buffalo.cse605.list.fine;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.Element;
import edu.buffalo.cse605.list.Writer;

public class CursorFine<T> extends Cursor<T> {
	
	public CursorFine(ElementFine<T> start) {
		super(start);
		this.curr = start; 
	}
	
	@Override
	public void next() {
		Element<T> curr;
		while (true) {
			curr = this.curr;
			if ( this.curr.nextlock.tryLock() ) {
				this.curr = curr.next();
				curr.nextlock.unlock();
				break;
			} else {
				Thread.yield();
			}
		}
	}

	@Override
	public void prev() {
		Element<T> curr;
		while (true) {
			curr = this.curr;
			if ( this.curr.prevlock.tryLock() ) {
				this.curr = curr.prev();
				curr.prevlock.unlock();
				break;
			} else {
				Thread.yield();
			}
		}
	}

	
	@Override
	public Writer<T> writer() {
		return new WriterFine<T>(this);
	}
}
