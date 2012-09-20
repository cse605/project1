package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;
import edu.buffalo.cse605.list.FDList;

public class insertLeftCoarse  extends Thread{
     private Cursor<String> c;
     private FDList<String> f;
     private int els;
     
     
     public insertLeftCoarse(FDList<String> f, Cursor<String> c, int els){
    	 this.els = els;
    	 this.f = f;
    	 this.c = c;
     }
     
    @Override
	public void run() {
    	 synchronized(f) {
	    	 for(int j=0; j < els; j++){
	        	 c.writer().insertBefore("beautiful" + j);
	         }
    	 }
     }
}
