package edu.buffalo.cse605.list.customrw;

import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import edu.buffalo.cse605.list.Writer;
import edu.buffalo.cse605.lock.ReadWriteLock;

public class WriterRW<T> extends Writer<T> { 
 	
	public WriterRW(CursorRW<T> cursor) {
		super(cursor);
		this.cursor = cursor;
	}

	@Override
	public boolean insertBefore(T val) {
		ElementRW<T> e = new ElementRW<T>(val);
		ElementRW<T> prev;
		ElementRW<T> curr;
		try {
			while ( true ) {
				prev = (ElementRW<T>) cursor.getprev();
				curr = (ElementRW<T>) cursor.curr();
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
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cursor.prev();
		return true;
	}


	@Override
	public boolean insertAfter(T val) {
		ElementRW<T> e = new ElementRW<T>(val);
		ElementRW<T> next;
		ElementRW<T> curr;
		WriteLock wnext;
		WriteLock wcurr;
		try {
			while ( true ) {
				next = (ElementRW<T>) cursor.getnext();
				curr = (ElementRW<T>) cursor.curr();
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
					next.rwcprevlock.lockWrite();
					curr.rwcnextlock.lockWrite();
					break;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		cursor.next();
		return true;
	}

	@Override
	public boolean delete() {
		ElementRW<T> prev;
		ElementRW<T> curr;
		ElementRW<T> next;
		
		try {
			while ( true ) {
				prev = (ElementRW<T>) cursor.getprev();
				next = (ElementRW<T>) cursor.getnext();
				curr = (ElementRW<T>) cursor.curr();
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
