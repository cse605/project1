package edu.buffalo.cse605.test;

import edu.buffalo.cse605.list.fine.CursorFine;
import edu.buffalo.cse605.list.fine.FDListFine;

public class testThread extends Thread{
	
	CursorFine<String> c;
	public testThread(FDListFine<String> a){
		c = a.freader( a.head() );
	}
	public void run(){
		
//		c.fwriter().insertBefore("good");
//	       
//        c.fwriter().insertAfter("hi2");
        
        for(int i=0;i<50;i++){
        	System.out.println("this is the  "+i);
        c.fwriter().insertAfter("bad" + i);
        }
        
        
       //catch(InterruptedException e){}
//        for(int i=0;i<52;i++)
//          c.fwriter().delete();
       // c.fwriter().delete();
        //c.fwriter().delete();
        //c.fwriter().delete();
        
	}
}
