package edu.buffalo.cse605.coarselist.src;

import edu.buffalo.cse605.list.src.*;
public class coarseWriter<T> {
private coarseCursor<T> cursor; 
	
	public coarseWriter(coarseCursor<T> cursor) {
		this.cursor=cursor;
	}

	
	public boolean insertBefore(T val) {
		//System.out.println("insert before");
		synchronized(coarseFDList.class) {
			Element<T> e = new Element<T>(val);
			if(cursor.curr()==null)
				cursor.curr(e);
			else{
			cursor.curr().addBefore(e);
			cursor.prev();
			}
			return true;
			
		}
		
	}


	public boolean insertAfter(T val) {
		//System.out.println("insert after");
		synchronized(coarseFDList.class){
		Element<T> e = new Element<T>(val);
		if ( cursor.curr() == null ) {
			 cursor.curr(e);
			 
		} else {
			cursor.curr().addAfter(e);
			cursor.next();
		}
		return true;
		}
	}

	public boolean delete() {
		//System.out.println("delete");
		 synchronized(coarseFDList.class){
		if(cursor.curr()==null)
			throw new Error("the list is empty");
		else if(cursor.getnext()==cursor.getprev() && cursor.getnext()==cursor.curr()){
		    System.out.println("delete "+cursor.curr().value());//only one element in the list
			cursor.curr(null);
		}
		else{
			cursor.curr().delete();
			cursor.curr(cursor.curr().prev());
		}
		// TODO Auto-generated method stub
		return true;
		 }
	}

}
