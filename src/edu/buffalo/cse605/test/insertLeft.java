package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.FDList;
import edu.buffalo.cse605.list.fine.CursorFine;
import edu.buffalo.cse605.list.fine.FDListFine;
import edu.buffalo.cse605.list.rw.CursorRW;
import edu.buffalo.cse605.list.rw.FDListRW;

public class insertLeft  extends Thread{
     public CursorFine<String> c;
     int i;
     public insertLeft(FDListFine<String> f, int i){
    	 this.i = i;
    	 c = f.reader(f.head());
     }
     
     @Override
	public void run() {
    	 for(int j=0; j < 100000/i; j++){
        	 c.writer().insertBefore("beautiful" + j);
         }
     }
}
