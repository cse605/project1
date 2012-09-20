package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;

public class insertLeft  extends Thread{
     private Cursor<String> c;
     private int els;

     public insertLeft(Cursor<String> c, int els){
    	 this.els = els;
    	 this.c = c;
     }
     
    @Override
	public void run() {
    	 for(int j=0; j < els; j++){
        	 c.writer().insertBefore("beautiful" + j);
         }
     }
}
