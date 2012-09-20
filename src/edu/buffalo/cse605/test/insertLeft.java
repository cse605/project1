package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;

public class insertLeft  extends Thread{
     private Cursor<String> c;
     private int nt;
     
     public insertLeft(int nt) {
    	 this.nt = nt;
     }
     
     public insertLeft(Cursor<String> c, int nt){
    	 this(nt);
    	 this.c = c;
     }
     
     @Override
	public void run() {
    	 for(int j=0; j < 100000/nt; j++){
        	 c.writer().insertBefore("beautiful" + j);
         }
     }
}
