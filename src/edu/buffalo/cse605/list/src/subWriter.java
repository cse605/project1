package edu.buffalo.cse605.list.src;

public class subWriter<T> extends Writer<T> {
    private Cursor<T> cursor; 
	
	public subWriter(Cursor<T> cursor) {
		super(cursor);
	}

	@Override
	public boolean insertBefore(T val) {
		//System.out.println("insert before");
		synchronized(FDList.class) {
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

	@Override
	public boolean insertAfter(T val) {
		//System.out.println("insert after");
		synchronized(FDList.class){
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

	@Override
	public boolean delete() {
		//System.out.println("delete");
		 synchronized(FDList.class){
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
