package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.fine.CursorFine;
import edu.buffalo.cse605.list.fine.FDListFine;

public class insertLeft  extends Thread{
     public CursorFine<String> c;
     int i;
     public insertLeft(FDListFine<String> f, int i){
    	 this.i = i;
    	 c = f.reader(f.head());
     }
     
     public void run() {
    	 for(int j=0; j < 100000/i; j++){
        	 c.writer().insertBefore("beautiful" + j);
         }
     }
}
