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
		 this.curr = curr.next();
	}
    
	public ElementFine<T> getnext(){
		return this.curr.next();
	}
	
	public ElementFine<T> getprev(){
		return this.curr().prev();
	}

	public void prev() {
		this.curr = curr.prev();
	}

	
	public WriterFine<T> writer() {
		return writer;
	}

}
