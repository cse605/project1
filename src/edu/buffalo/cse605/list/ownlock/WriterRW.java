package edu.buffalo.cse605.list.ownlock;

import edu.buffalo.cse605.list.*;

public class WriterRW<T> extends Writer<T>{
	
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
				prev.rwnextlock.lockwrite();
				curr.rwprevlock.lockwrite();
				// Make sure they are still pointing to the ones they were supposed to point
				// This messes the performance
				if ( prev.next() == curr && 
					 curr.prev() == prev ) {
					if ( cursor.curr() == null ) {
						 cursor.curr(e);
					} else {
						curr.addBefore(e);
					}
					curr.rwprevlock.unlockwrite();
					prev.rwnextlock.unlockwrite();
					break;
				} 
				curr.rwprevlock.unlockwrite();
				prev.rwnextlock.unlockwrite();
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
		try {
			while ( true ) {
				curr = (ElementRW<T>) cursor.curr();
				next = (ElementRW<T>) cursor.getnext();
				System.out.println(curr.value());
				curr.rwnextlock.lockwrite();
				next.rwprevlock.lockwrite();
				
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
					next.rwprevlock.unlockwrite();
					curr.rwnextlock.unlockwrite();
					break;
				}
				next.rwprevlock.unlockwrite();
				curr.rwnextlock.unlockwrite();
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
				prev.rwnextlock.lockwrite();
				curr.rwprevlock.lockwrite();
				curr.rwnextlock.lockwrite();
				next.rwprevlock.lockwrite();
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
					prev.rwnextlock.unlockwrite();
					curr.rwprevlock.unlockwrite();
					curr.rwnextlock.unlockwrite();
					next.rwprevlock.unlockwrite();
					break;
				}
				prev.rwnextlock.unlockwrite();
				curr.rwprevlock.unlockwrite();
				curr.rwnextlock.unlockwrite();
				next.rwprevlock.unlockwrite();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}	
}
