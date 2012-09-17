package edu.buffalo.cse605.list.src;

public class WriterFine<T> extends Writer<T> {
	private Cursor<T> cursor; 
	
	public WriterFine(Cursor<T> cursor) {
		super(cursor); 
	}

	@Override
	public boolean insertBefore(T val) {
		//System.out.println("insert before");
		Element<T> e = new Element<T>(val);
		if(cursor.curr()==null) {
			// acquire lock here for cursor.curr().prev()
			// acquire lock here for cursor.curr
			cursor.curr(e);
		} else {
		cursor.curr().addBefore(e);
		cursor.prev();
		}
		return true;
	}

	@Override
	public boolean insertAfter(T val) {
		//System.out.println("insert after");
		Element<T> e = new Element<T>(val);
		if ( cursor.curr() == null ) {
			 cursor.curr(e);
			 
		} else {
			cursor.curr().addAfter(e);
			cursor.next();
		}
		return true;
	}

	@Override
	public boolean delete() {
		//System.out.println("delete");
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
