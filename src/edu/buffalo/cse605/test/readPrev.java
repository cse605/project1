package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;

public class readPrev  extends Thread{
	public Cursor<String> c;
    
    public readPrev(Cursor<String> c){
   	 this.c = c;
    }
     
     @Override
	public void run(){
    	 while(c.curr() != null) {
    		 c.prev();
    	 }
     }
}
