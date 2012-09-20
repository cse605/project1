package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.fine.CursorFine;
import edu.buffalo.cse605.list.fine.FDListFine;

public class readNext  extends Thread{
     public CursorFine<String> c;
     int i;
     public readNext(FDListFine<String> f){
    	 c = f.reader(f.head());
     }
     
     @Override
	public void run(){
    	 while(c.curr() != null) {
    		 c.next();
    	 }
     }
}
