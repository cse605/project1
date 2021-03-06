package edu.buffalo.cse605.list.rw;

import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.Element;
import edu.buffalo.cse605.list.Writer;

public class CursorRW<T> extends Cursor<T> {
	
	public CursorRW(ElementRW<T> start, FDListRW<T> list) {
		super(start);
		this.curr = start; 
		this.list = list;
	}
	
	@Override
	public void next() {
		Element<T> curr;
		ReadLock lock;
		while (true) {
			curr = this.curr;
			lock = curr.rnextlock;
			if ( lock.tryLock() ) {
				this.curr = curr.next();
				lock.unlock();
				break;
			} else {
				Thread.yield();
			}
		}
	}

	@Override
	public void prev() {
		Element<T> curr;
		ReadLock lock;
		while (true) {
			curr = this.curr;
			lock = curr.rprevlock;
			if ( lock.tryLock() ) {
				this.curr = curr.prev();
				lock.unlock();
				break;
			} else {
				Thread.yield();
			}
		}
	}

	
	@Override
	public Writer<T> writer() {
		return new WriterRW<T>(this);
	}
}
