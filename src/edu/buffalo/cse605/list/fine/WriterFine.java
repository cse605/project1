package edu.buffalo.cse605.list.fine;

import edu.buffalo.cse605.list.Writer;

public class WriterFine<T> extends Writer<T> { 
 	
	public WriterFine(CursorFine<T> cursor) {
		super(cursor);
		this.cursor = cursor;
	}

	@Override
	public boolean insertBefore(T val) {
		ElementFine<T> e = new ElementFine<T>(val);
		ElementFine<T> prev;
		ElementFine<T> curr;
		try {
			while ( true ) {
				prev = (ElementFine<T>) cursor.getprev();
				curr = (ElementFine<T>) cursor.curr();
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( prev.nextlock.tryLock() &&
					 curr.prevlock.tryLock() &&
					 prev.next() == curr && 
					 curr.prev() == prev ) {
					if ( cursor.curr() == null ) {
						 cursor.curr(e);
					} else {
						curr.addBefore(e);
					}
					curr.prevlock.unlock();
					prev.nextlock.unlock();
					break;
				} else {
					if ( curr.prevlock.isHeldByCurrentThread() ) {
						curr.prevlock.unlock();
					}
					if ( prev.nextlock.isHeldByCurrentThread() ) {
						prev.nextlock.unlock();
					}
					Thread.yield();
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cursor.prev();
		cursor.list.count.getAndIncrement();
		return true;
	}


	@Override
	public boolean insertAfter(T val) {
		ElementFine<T> e = new ElementFine<T>(val);
		ElementFine<T> next;
		ElementFine<T> curr;
		try {
			while ( true ) {
				next = (ElementFine<T>) cursor.getnext();
				curr = (ElementFine<T>) cursor.curr();
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( next.prevlock.tryLock() &&
					 curr.nextlock.tryLock() &&
					 next.prev() == cursor.curr() && 
					 curr.next() == next ) {
					if ( cursor.curr() == null ) {
						 cursor.curr(e);
					} else {
						curr.addAfter(e);
					}
					next.prevlock.unlock();
					curr.nextlock.unlock();
					break;
				} else {
					if ( curr.nextlock.isHeldByCurrentThread() ) {
						curr.nextlock.unlock();
					}
					if ( next.prevlock.isHeldByCurrentThread() ) {
						next.prevlock.unlock();
					}
					Thread.yield();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		cursor.next();
		cursor.list.count.getAndIncrement();
		return true;
	}

	@Override
	public boolean delete() {
		ElementFine<T> prev;
		ElementFine<T> curr;
		ElementFine<T> next;
		
		try {
			while ( true ) {
				prev = (ElementFine<T>) cursor.getprev();
				next = (ElementFine<T>) cursor.getnext();
				curr = (ElementFine<T>) cursor.curr();
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( prev.nextlock.tryLock() &&
					 curr.prevlock.tryLock() &&
					 curr.nextlock.tryLock() &&
					 next.prevlock.tryLock() &&
					 prev.next() == curr && 
					 curr.prev() == prev &&
					 curr.next() == next &&
					 next.prev() == curr ) {
					if (cursor.curr() == null) { 
						throw new Error("the list is empty");
					} else if (cursor.getnext() == cursor.getprev() && cursor.getnext() == cursor.curr()) {
						cursor.curr(null);
					} else {
						// 	Delete
						cursor.curr().delete();
						// Move the cursor to previous
						cursor.curr(prev);
					}
					prev.nextlock.unlock();
					next.prevlock.unlock();
					break;
				} else {
					if ( prev.nextlock.isHeldByCurrentThread() ) {
						prev.nextlock.unlock();
					}
					if ( next.prevlock.isHeldByCurrentThread() ) {
						next.prevlock.unlock();
					}
					Thread.yield();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cursor.list.count.getAndDecrement();
		return true;
	}
	
}
