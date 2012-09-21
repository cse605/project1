package edu.buffalo.cse605.list.ownlock;

import java.util.concurrent.atomic.*;
import java.util.*;


public class readwritelock {
	AtomicInteger readernumber=new AtomicInteger(0);
	AtomicInteger writewait=new AtomicInteger(0);
    AtomicBoolean writeenter=new AtomicBoolean(false);
  
    
    private long currentwrite;
    public readwritelock(){};
    
    public synchronized void lockread(){
    	System.out.println(writewait.get());
    	if(!(writewait.get()==0)){
    		
    		try{ wait();}catch(InterruptedException e){e.printStackTrace();}
    	}   	
    		
//    		if(writeenter.get()==true){
//    		if(Thread.currentThread().getId()==currentwrite){
//    			readwnumber.incrementAndGet();
//    		}
//    		}
//    		else {
    			while(writeenter.get()==true){}
    			readernumber.incrementAndGet();
    			
//    		}
    		
    	 
    }
     
    
    
    public void lockwrite(){
    	writewait.incrementAndGet();
    	
    	synchronized(this){
    		while(writeenter.get()==true){System.out.println("waiting");}
    		
    		writeenter.set(true);
    		writewait.decrementAndGet();
    		   notifyAll();
    			
            currentwrite=Thread.currentThread().getId();
            
    }
    	
    }
    
    
    public void unlockread() {
    	readernumber.decrementAndGet();//the programmer should pay attention to this
    }
    
    public void unlockwrite(){
    	writeenter.compareAndSet(true,false);
    }
    
    
}
