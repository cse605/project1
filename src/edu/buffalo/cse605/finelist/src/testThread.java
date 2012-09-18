package edu.buffalo.cse605.finelist.src;

public class testThread extends Thread{
	
	fineCursor<String> c;
	public testThread(fineFDList<String> a){
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
