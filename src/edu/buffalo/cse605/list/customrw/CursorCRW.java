package edu.buffalo.cse605.list.customrw;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.Element;
import edu.buffalo.cse605.list.Writer;

public class CursorCRW<T> extends Cursor<T> {
	
	public CursorCRW(ElementCRW<T> start) {
		super(start);
		this.curr = start; 
	}
	
	@Override
	public void next() {
		Element<T> curr;
		while (true) {
			curr = this.curr;
			try {
				curr.rwcnextlock.lockRead();
				this.curr = curr.next();
				curr.rwcnextlock.unlockRead();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	public void prev() {
		Element<T> curr;
		while (true) {
			curr = this.curr;
			try {
				curr.rwcprevlock.lockRead();
				this.curr = curr.prev();
				curr.rwcprevlock.unlockRead();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	
	@Override
	public Writer<T> writer() {
		return new WriterCRW<T>(this);
	}
}
