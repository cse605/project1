package edu.buffalo.cse605.list.fine;

public class WriterFine<T> {
    private CursorFine<T> cursor; 
 	
	public WriterFine(CursorFine<T> cursor) {
		this.cursor=cursor;
	}

	
	public boolean insertBefore(T val) {
			ElementFine<T> e = new ElementFine<T>(val);
			boolean prevl=false;
			boolean nextl=false;
			boolean unchanged=true;
			ElementFine<T> t1=cursor.curr();
			ElementFine<T> t2=cursor.curr();
			try {
				while((!(prevl && nextl)) && unchanged){
					if(prevl){
						cursor.curr().prev().nextlock.unlock();
					}
					if(nextl) {
						cursor.curr().prevlock.unlock();
					}
					try {
						nextl = cursor.curr().prevlock.tryLock();
					  	t1 = cursor.curr();
					  	prevl = cursor.curr().prev().nextlock.tryLock();	
					  	t2 = cursor.curr().prev();
					} catch (NullPointerException e1){
						//cursor.curr(e);
						throw new Error("the cursor points to null");
					}
					if (nextl && prevl) {
						if(t1.prev()==t2) {
							unchanged = true;
						} else {
							unchanged=false;
						}
					}
				}
				cursor.curr().addBefore(e);
				cursor.prev();
			} finally {
				if (nextl) {
					cursor.curr().next().prevlock.unlock();
				}
				if (prevl) {
					cursor.curr().prev().nextlock.unlock();
				}
			}
			return true;		
			
		
	}


	public boolean insertAfter(T val) {
		//System.out.println("insert after "+val+" "+Thread.currentThread().getId());
		boolean prevl=false;
		boolean nextl=false;
		boolean unchanged=true;
		ElementFine<T> t1=null;
		ElementFine<T> t2=null;
		ElementFine<T> e = new ElementFine<T>(val);
		try{
			while(!(prevl&&nextl)&&unchanged){
				if(prevl){cursor.curr().next().prevlock.unlock();}
				if(nextl){cursor.curr().nextlock.unlock();}
			  try{nextl=cursor.curr().nextlock.tryLock();
			  t1=cursor.curr();
			 prevl=cursor.curr().next().prevlock.tryLock();	
			  t2=cursor.curr().next();
			  }catch(NullPointerException e1){
				  //cursor.curr(e);
				  throw new Error("the cursor point to null");
			  }
			  if(nextl&&prevl){
				  if(t1.next()==t2)
					  unchanged=true;
				  else
					  unchanged=false;
			  }
			}
			
			 
				cursor.curr().addAfter(e);
				cursor.next();
			
		}
		finally{
			if(prevl){cursor.curr().next().prevlock.unlock();}
			if(nextl){cursor.curr().prev().nextlock.unlock();}
			
		}
		return true;
		
	}

	public boolean delete() {
		//System.out.println("delete "+Thread.currentThread().getId());
		//System.out.println("delete");
		boolean prev1=false;
		boolean prev2=false;
		boolean next1=false;
		boolean next2=false;
		ElementFine t1=null, t2=null, t3=null;
		boolean unchanged=true;
		try{
			while(!(prev1&&prev2&&next1&&next2)&&unchanged){
			if(next1){cursor.curr().prev().nextlock.unlock();}
			if(prev1){cursor.curr().prevlock.unlock();}
			if(next2){cursor.curr().nextlock.unlock();}
			if(prev2){
				cursor.curr().next().prevlock.unlock();
			}
			try{next1=cursor.curr().prev().nextlock.tryLock();
			    t1=cursor.curr().prev();
			prev1=cursor.curr().prevlock.tryLock();
			    t2=cursor.curr();
			next2=cursor.curr().nextlock.tryLock();
			   
			prev2=cursor.curr().next().prevlock.tryLock();
			t3=cursor.curr().next();
			}catch(NullPointerException e){
				throw new Error("the list is empty");
			}
			if(next1&&next2&&prev1&&prev2){
				if(t2.prev()==t1&&t2.next()==t3)
					unchanged=true;
				else
					unchanged=false;
			}
			}
		  if(cursor.getnext()==cursor.getprev() && cursor.getnext()==cursor.curr()){
			    //System.out.println("delete "+cursor.curr().value());//only one element in the list
				cursor.curr(null);
			}
			else{
				cursor.curr().delete();
				cursor.curr(cursor.curr().prev());
			}
			
		}
		finally{
			if(cursor.curr()!=null){
			if(next1){cursor.curr().nextlock.unlock();}
			if(prev2){cursor.curr().next().prevlock.unlock();}
			}
		}
		
		// TODO Auto-generated method stub
		return true;
	}
	
}
