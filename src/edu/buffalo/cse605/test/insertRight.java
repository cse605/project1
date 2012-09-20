package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;

public class insertRight  extends Thread{
	private Cursor<String> c;
    private int nt;
    
    public insertRight(int nt) {
   	 this.nt = nt;
    }
    
    public insertRight(Cursor<String> c, int nt){
   	 this(nt);
   	 this.c = c;
    }
     
     @Override
	public void run() {
    	 for(int j=0; j < 1000000/nt; j++){
        	 c.writer().insertAfter("beautiful" + j);
         }
     }
}
