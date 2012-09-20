package edu.buffalo.cse605.list.fine;


public class WriterFine<T> {
    private CursorFine<T> cursor; 
 	
	public WriterFine(CursorFine<T> cursor) {
		this.cursor=cursor;
	}

	public boolean insertBefore(T val) {
		ElementFine<T> e = new ElementFine<T>(val);
		ElementFine<T> prev;
		ElementFine<T> curr;
		try {
			while ( true ) {
				prev = cursor.getprev();
				curr = cursor.curr();
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
					cursor.prev();
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
		return true;
	}


	public boolean insertAfter(T val) {
		ElementFine<T> e = new ElementFine<T>(val);
		ElementFine<T> next;
		ElementFine<T> curr;
		try {
			while ( true ) {
				next = cursor.getnext();
				curr = cursor.curr();
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
						cursor.next();
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
		return true;
	}

	public boolean delete() {
		ElementFine<T> prev;
		ElementFine<T> curr;
		ElementFine<T> next;
		
		try {
			while ( true ) {
				prev = cursor.getprev();
				next = cursor.getnext();
				curr = cursor.curr();
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
		return true;
	}
	
}
