package edu.buffalo.cse605.finelist.src;



public class fineCursor<T>  {
	private fineElement<T> curr;
	private fineWriter<T> writer = new fineWriter<T>(this);
	
	public fineCursor(fineElement<T> start) {
		this.curr = start;
		
		// Find out: Does it reference or assign ?
	}
	
	// TODO: needs to be not exposed to public
	public fineElement<T> curr(fineElement<T> curr) {
		this.curr = curr;
		return curr;
	}
	
	
	public fineElement<T> curr() {
		return this.curr;
	}

	
	public void next() {
		 this.curr = curr.next();
	}
    
	
	
	public fineElement getnext(){
		return this.curr.next();
	}
	
	public fineElement getprev(){
		return this.curr().prev();
	}

	public void prev() {
		this.curr = curr.prev();
	}

	
	public fineWriter<T> fwriter() {
		return writer;
	}

}
