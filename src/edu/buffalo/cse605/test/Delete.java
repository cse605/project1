package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;

public class Delete  extends Thread{
     private Cursor<String> c;
     private int els;

     public Delete(Cursor<String> c, int els){
    	 this.els = els;
    	 this.c = c;
     }
     
    @Override
	public void run() {
    	 for(int j=0; j < els; j++){
    		 if ( c.curr() != c.list.head() ) {
    			 c.writer().delete();
    		 }
         }
     }
}
