package edu.buffalo.cse605.finelist.src;

public class fineWriter<T> {
    private fineCursor<T> cursor; 
 	
	public fineWriter(fineCursor<T> cursor) {
		this.cursor=cursor;
	}

	
	public boolean insertBefore(T val) {
		//System.out.println("insert before");
		System.out.println("insertbefore "+val+" "+Thread.currentThread().getId());
			fineElement<T> e = new fineElement<T>(val);
			boolean prevl=false;
			boolean nextl=false;
			boolean unchanged=true;
			fineElement<T> t1=cursor.curr();
			fineElement<T> t2=cursor.curr();
			try {
				while((!(prevl&&nextl))&&unchanged){
					if(prevl){cursor.curr().prev().nextlock.unlock();}
					if(nextl){cursor.curr().prevlock.unlock();}
				  try{nextl=cursor.curr().prevlock.tryLock();
				       t1=cursor.curr();
				     prevl=cursor.curr().prev().nextlock.tryLock();	
				       t2=cursor.curr().prev();
				  }catch(NullPointerException e1){
					  cursor.curr(e);
				  }
				  if(nextl&&prevl){
					  if(t1.prev()==t2)
						  unchanged=true;
					  else
						  unchanged=false;
				  }
				}
				
				//System.out.println(cursor.curr());
				//System.out.println(cursor.curr().prevlock);
				if(cursor.curr()==null)
					cursor.curr(e);
				else{
				cursor.curr().addBefore(e);
				cursor.prev();
				//System.out.println(cursor.curr());
				} 
				
			}
			finally{
//				System.out.println("access succesfully"+cursor.curr().prev()+cursor.curr().next());
//				System.out.println(cursor.curr().prev().nextlock);
//				System.out.println(cursor.curr().next().prevlock);
////				if(nextl){
//					//cursor.curr().prev().nextlock.notifyAll();
//					cursor.curr().prev().nextlock.notifyAll();
////				}
////				if(prevl){
//					//cursor.curr().next().prevlock.await();
//					cursor.curr().next().prevlock.notifyAll();
////				}
//				
				if(nextl){cursor.curr().next().prevlock.unlock();}
				if(prevl){cursor.curr().prev().nextlock.unlock();
					
				}
			}
			
			return true;		
			
		
	}


	public boolean insertAfter(T val) {
		//System.out.println("insert after "+val+" "+Thread.currentThread().getId());
		boolean prevl=false;
		boolean nextl=false;
		fineElement<T> e = new fineElement<T>(val);
		try{
			while(!(prevl&&nextl)){
				if(prevl){cursor.curr().next().prevlock.unlock();}
				if(nextl){cursor.curr().nextlock.unlock();}
			  try{nextl=cursor.curr().nextlock.tryLock();
			 prevl=cursor.curr().next().prevlock.tryLock();		
			  }catch(NullPointerException e1){
				  cursor.curr(e);
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
		try{
			while(!(prev1&&prev2&&next1&&next2)){
			if(next1){cursor.curr().prev().nextlock.unlock();}
			if(prev1){cursor.curr().prevlock.unlock();}
			if(next2){cursor.curr().nextlock.unlock();}
			if(prev2){
				cursor.curr().next().prevlock.unlock();
			}
			try{next1=cursor.curr().prev().nextlock.tryLock();
			prev1=cursor.curr().prevlock.tryLock();
			next2=cursor.curr().nextlock.tryLock();
			prev2=cursor.curr().next().prevlock.tryLock();
			}catch(NullPointerException e){
				throw new Error("the list is empty");
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