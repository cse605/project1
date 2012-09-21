 package edu.buffalo.cse605.list.ownlock;

import edu.buffalo.cse605.list.*;

public class CursorRW<T> extends Cursor<T> {
	
	 public CursorRW(ElementRW<T> start) {
		super(start);
		this.curr = start; 
	}
	
	@Override
	public void next() {		
				this.curr = curr.next();
	}
	
	public void readnext(){			
			try{this.curr.rwcnextlock.lockRead();
			this.curr=this.curr.next();}catch(InterruptedException e){e.printStackTrace();}
		}
	

	@Override
	public void prev() {
				this.curr = curr.prev();	
	}

	public void readprev(){
		try{this.curr.rwcprevlock.lockRead();
		     this.curr=this.curr.prev();}catch(InterruptedException e){e.printStackTrace();}
	}
	@Override
	public Writer<T> writer() {
		return new WriterRW<T>(this);
	}
}
