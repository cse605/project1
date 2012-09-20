package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.Cursor;

public class readNext  extends Thread{
     public Cursor<String> c;
     
     public readNext(Cursor<String> c){
    	 this.c = c;
     }
     
     @Override
	public void run(){
    	 while(c.curr() != null) {
    		 c.next();
    	 }
     }
}
