package edu.buffalo.cse605.list.fine;


public class WriterFine<T> {
    private CursorFine<T> cursor; 
 	
	public WriterFine(CursorFine<T> cursor) {
		this.cursor=cursor;
	}

	public boolean insertBefore(T val) {
		ElementFine<T> e = new ElementFine<T>(val);
		ElementFine<T> prev = cursor.getprev();
		ElementFine<T> curr = cursor.curr();
		try {
			while ( prev.nextlock.tryLock() &&
					curr.prevlock.tryLock() ) {
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( prev.next() == curr && 
					curr.prev() == prev ) {
					if ( cursor.curr() == null ) {
						 cursor.curr(e);
					} else {
						curr.addBefore(e);
						cursor.prev();
					}
					cursor.getnext().prevlock.unlock();
					prev.nextlock.unlock();
					break;
				} else {
					throw new Exception("inconsitent");
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}


	public boolean insertAfter(T val) {
		ElementFine<T> e = new ElementFine<T>(val);
		ElementFine<T> next = cursor.getnext();
		ElementFine<T> curr = cursor.curr();
		try {
			while ( next.prevlock.tryLock() &&
					curr.nextlock.tryLock() ) {
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
					cursor.getprev().nextlock.unlock();
					next.prevlock.unlock();
					break;
				} else {
					throw new Exception("inconsitent");
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		return true;
	}

	public boolean delete() {
		ElementFine<T> prev = cursor.getprev();
		ElementFine<T> curr = cursor.curr();
		ElementFine<T> next = cursor.getnext();
		
		try {
			while ( prev.nextlock.tryLock() &&
					curr.prevlock.tryLock() &&
					curr.nextlock.tryLock() &&
					next.prevlock.tryLock() ) {
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( prev.next() == curr && 
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
					throw new Exception("inconsitent");
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}
	
}
