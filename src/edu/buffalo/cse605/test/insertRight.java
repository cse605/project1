package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;

public class insertRight  extends Thread{
    private Cursor<String> c;
    private int els;

    public insertRight(Cursor<String> c, int els){
   	 this.els = els;
   	 this.c = c;
    }
    
   @Override
	public void run() {
   	 for(int j=0; j < els; j++){
       	 c.writer().insertAfter("beautiful" + j);
        }
    }
}
