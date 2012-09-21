package edu.buffalo.cse605.list.customrw;

import edu.buffalo.cse605.list.Writer;

public class WriterCRW<T> extends Writer<T> { 
 	
	public WriterCRW(CursorCRW<T> cursor) {
		super(cursor);
		this.cursor = cursor;
	}

	@Override
	public boolean insertBefore(T val) {
		ElementCRW<T> e = new ElementCRW<T>(val);
		ElementCRW<T> prev;
		ElementCRW<T> curr;
		try {
			while ( true ) {
				prev = (ElementCRW<T>) cursor.getprev();
				curr = (ElementCRW<T>) cursor.curr();
				prev.rwcnextlock.lockWrite();
				curr.rwcprevlock.lockWrite();
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( prev.next() == curr && 
					 curr.prev() == prev ) {
					if ( cursor.curr() == null ) {
						 cursor.curr(e);
					} else {
						curr.addBefore(e);
					}
					curr.rwcprevlock.unlockWrite();
					prev.rwcnextlock.unlockWrite();
					break;
				} 
				curr.rwcprevlock.unlockWrite();
				prev.rwcnextlock.unlockWrite();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cursor.prev();
		return true;
	}


	@Override
	public boolean insertAfter(T val) {
		ElementCRW<T> e = new ElementCRW<T>(val);
		ElementCRW<T> next;
		ElementCRW<T> curr;
		try {
			while ( true ) {
				next = (ElementCRW<T>) cursor.getnext();
				curr = (ElementCRW<T>) cursor.curr();
				next.rwcprevlock.lockWrite();
				curr.rwcnextlock.lockWrite();
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( next.prev() == cursor.curr() && 
					 curr.next() == next ) {
					if ( cursor.curr() == null ) {
						 cursor.curr(e);
					} else {
						curr.addAfter(e);
						cursor.next();
					}
					next.rwcprevlock.unlockWrite();
					curr.rwcnextlock.unlockWrite();
					break;
				}
				next.rwcprevlock.unlockWrite();
				curr.rwcnextlock.unlockWrite();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		cursor.next();
		return true;
	}

	@Override
	public boolean delete() {
		ElementCRW<T> prev;
		ElementCRW<T> curr;
		ElementCRW<T> next;
		
		try {
			while ( true ) {
				prev = (ElementCRW<T>) cursor.getprev();
				next = (ElementCRW<T>) cursor.getnext();
				curr = (ElementCRW<T>) cursor.curr();
				prev.rwcnextlock.lockWrite();
				curr.rwcprevlock.lockWrite();
				curr.rwcnextlock.lockWrite();
				next.rwcprevlock.lockWrite();
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( prev.next() == curr && 
					 curr.prev() == prev &&
					 curr.next() == next &&
					 next.prev() == curr ) {
					if (cursor.curr() == null) { 
						throw new Error("the list is empty");
					} else if ( next == prev && next == curr ) {
						cursor.curr(null);
					} else {
						// 	Delete
						curr.delete();
						// Move the cursor to previous
						cursor.curr(prev);
					}
					prev.rwcnextlock.unlockWrite();
					curr.rwcprevlock.unlockWrite();
					curr.rwcnextlock.unlockWrite();
					next.rwcprevlock.unlockWrite();
					break;
				}
				prev.rwcnextlock.unlockWrite();
				curr.rwcprevlock.unlockWrite();
				curr.rwcnextlock.unlockWrite();
				next.rwcprevlock.unlockWrite();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}	
}
