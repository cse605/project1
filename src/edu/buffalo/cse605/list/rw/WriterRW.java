package edu.buffalo.cse605.list.rw;

import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import edu.buffalo.cse605.list.Writer;

public class WriterRW<T> extends Writer<T> { 
 	
	public WriterRW(CursorRW<T> cursor) {
		super(cursor);
		this.cursor = cursor;
	}

	@Override
	public boolean insertBefore(T val) {
		ElementRW<T> e = new ElementRW<T>(val);
		ElementRW<T> prev, curr;
		WriteLock wprev, wcurr;
		try {
			while ( true ) {
				prev = (ElementRW<T>) cursor.getprev();
				curr = (ElementRW<T>) cursor.curr();
				wprev = prev.wnextlock;
				wcurr = curr.wprevlock;
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( wprev.tryLock() &&
					 wcurr.tryLock() &&
					 prev.next() == curr && 
					 curr.prev() == prev ) {
					if ( cursor.curr() == null ) {
						 cursor.curr(e);
					} else {
						curr.addBefore(e);
					}
					wcurr.unlock();
					wprev.unlock();
					cursor.prev();
					break;
				} else {
					if ( wcurr.isHeldByCurrentThread() ) {
						wcurr.unlock();
					}
					if ( wprev.isHeldByCurrentThread() ) {
						wprev.unlock();
					}
					Thread.yield();
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}


	@Override
	public boolean insertAfter(T val) {
		ElementRW<T> e = new ElementRW<T>(val);
		ElementRW<T> next, curr;
		WriteLock wnext, wcurr;
		try {
			while ( true ) {
				next = (ElementRW<T>) cursor.getnext();
				curr = (ElementRW<T>) cursor.curr();
				wnext = next.wprevlock;
				wcurr = curr.wnextlock;
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( wnext.tryLock() &&
					 wcurr.tryLock() &&
					 next.prev() == cursor.curr() && 
					 curr.next() == next ) {
					if ( cursor.curr() == null ) {
						 cursor.curr(e);
					} else {
						curr.addAfter(e);
						cursor.next();
					}
					wnext.unlock();
					wcurr.unlock();
					cursor.next();
					break;
				} else {
					if ( wcurr.isHeldByCurrentThread() ) {
						wcurr.unlock();
					}
					if ( wnext.isHeldByCurrentThread() ) {
						wnext.unlock();
					}
					Thread.yield();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		return true;
	}

	@Override
	public boolean delete() {
		ElementRW<T> prev, curr, next;
		WriteLock wnext, wcurrp, wcurrn, wprev;
		try {
			while ( true ) {
				prev = (ElementRW<T>) cursor.getprev();
				next = (ElementRW<T>) cursor.getnext();
				curr = (ElementRW<T>) cursor.curr();
				
				wprev = prev.wnextlock;
				wcurrp = curr.wprevlock;
				wcurrn = curr.wnextlock;
				wnext = next.wprevlock;
				
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( wprev.tryLock() &&
					 wcurrp.tryLock() &&
					 wcurrn.tryLock() &&
					 wnext.tryLock() &&
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
					wprev.unlock();
					wnext.unlock();
					break;
				} else {
					if ( wprev.isHeldByCurrentThread() ) {
						wprev.unlock();
					}
					if ( wnext.isHeldByCurrentThread() ) {
						wnext.unlock();
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
