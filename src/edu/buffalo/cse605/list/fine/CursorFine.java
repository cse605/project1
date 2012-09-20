package edu.buffalo.cse605.list.fine;



public class CursorFine<T>  {
	private ElementFine<T> curr;
	private WriterFine<T> writer = new WriterFine<T>(this);
	
	public CursorFine(ElementFine<T> start) {
		this.curr = start;
		
		// Find out: Does it reference or assign ?
	}
	
	// TODO: needs to be not exposed to public
	public ElementFine<T> curr(ElementFine<T> curr) {
		this.curr = curr;
		return curr;
	}
	
	
	public ElementFine<T> curr() {
		return this.curr;
	}

	
	public void next() {
		ElementFine<T> curr;
		while (true) {
			curr = this.curr;
			if ( this.curr.nextlock.tryLock() ) {
				this.curr = curr.next();
				curr.nextlock.unlock();
				break;
			} else {
				if ( curr.nextlock.isHeldByCurrentThread() ) {
					curr.nextlock.unlock();
				}
				Thread.yield();
			}
		}
	}
    
	public ElementFine<T> getnext(){
		return this.curr.next();
	}
	
	public ElementFine<T> getprev(){
		return this.curr().prev();
	}

	public void prev() {
		ElementFine<T> curr;
		while (true) {
			curr = this.curr;
			if ( this.curr.prevlock.tryLock() ) {
				this.curr = curr.prev();
				curr.prevlock.unlock();
				break;
			} else {
				if ( curr.prevlock.isHeldByCurrentThread() ) {
					curr.prevlock.unlock();
				}
				Thread.yield();
			}
		}
	}

	
	public WriterFine<T> writer() {
		return writer;
	}

}
