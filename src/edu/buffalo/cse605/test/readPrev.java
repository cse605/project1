package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.fine.CursorFine;
import edu.buffalo.cse605.list.fine.FDListFine;

public class readPrev  extends Thread{
     public CursorFine<String> c;
     int i;
     public readPrev(FDListFine<String> f){
    	 c = f.reader(f.head());
     }
     
     public void run(){
    	 while(c.curr() != null) {
    		 c.prev();
    	 }
     }
}
