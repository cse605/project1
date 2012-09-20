package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.FDList;

public class insertRightCoarse extends Thread{
     private Cursor<String> c;
     private FDList<String> f;
     private int nt;
     
     
     public insertRightCoarse(FDList<String> f, Cursor<String> c, int nt){
    	 this.nt = nt;
    	 this.f = f;
    	 this.c = c;
     }
     
     @Override
	public void run() {
    	 synchronized(f) {
	    	 for(int j=0; j < 200000/nt; j++){
	        	 c.writer().insertAfter("beautiful" + j);
	         }
    	 }
     }
}
