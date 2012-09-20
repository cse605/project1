package edu.buffalo.cse605.list.fine;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
public class ownlock {
   private  AtomicBoolean writeleftfirst=new AtomicBoolean(false);
    private  AtomicBoolean writerightfirst=new AtomicBoolean(false);
    private AtomicBoolean writewait=new AtomicBoolean(false);
    AtomicInteger  readnumber=new AtomicInteger(0);
    private Object judge=new Object();
  
    
    
    public ownlock(){};
    
    
    public void readlock(){
    	
    		if(readnumber.get()==0||readnumber.get()>5){
    		while(writeleftfirst.get()||writerightfirst.get()){
    		}
    		}
    		synchronized(ElementFine.class){
    		readnumber.incrementAndGet();
    		//System.out.println("now the number of reading thread is "+readnumber.get());
    	}
    }
    
   public void unreadlock(){
	   readnumber.decrementAndGet();
   }
   
   
   public void writeleftlock(){
	   writeleftfirst.set(true);
	   synchronized(ElementFine.class){
		   while(readnumber.get()>0){}
	   }
   }
   
   
   
   public void writerightlock(){
	   
	   writerightfirst.set(true);
	   synchronized(ElementFine.class){
		   while(readnumber.get()>0){}
	   }
   }
   
   
   public void unwriteleftlock(){
	   
		   writeleftfirst.set(false);
		  
   }
   public void unwriterightlock(){
	 
		   writerightfirst.set(false);
;		 
   }
}
