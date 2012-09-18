package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.fine.CursorFine;
import edu.buffalo.cse605.list.fine.FDListFine;

public class testThread2  extends Thread{
     public CursorFine<String> c;
     int i;
     public testThread2(FDListFine<String> f, int i){
    	 this.i = i;
    	 c = f.freader(f.head());
     }
     
     public void run(){
    	 long startTime = System.currentTimeMillis();
    	 for(int j=0; j < 10000/i; j++){
        	 c.fwriter().insertBefore("beautiful" + j);
         }
         long endTime   = System.currentTimeMillis();
         long totalTime = endTime - startTime;
         System.out.println("Thread-" + Thread.currentThread().getId() + " running time => " + totalTime);
     }
}
