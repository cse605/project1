package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.fine.CursorFine;
import edu.buffalo.cse605.list.fine.FDListFine;

public class insertRight  extends Thread{
     public CursorFine<String> c;
     int i;
     public insertRight(FDListFine<String> f, int i){
    	 this.i = i;
    	 c = f.reader(f.head());
     }
     
     @Override
	public void run() {
    	 for(int j=0; j < 100000/i; j++){
        	 c.writer().insertAfter("beautiful" + j);
         }
     }
}
