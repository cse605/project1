package edu.buffalo.cse605.list.fine;

public class testThread2  extends Thread{
     public CursorFine<String> c;
     public testThread2(FDListFine<String> f){
    	 c=f.freader(f.head());
     }
     public void run(){
    	 
    	 try{this.sleep(500);}catch(InterruptedException e){}
         for(int i=0;i<50;i++){
              c.fwriter().delete();
              System.out.println("this is the "+i +"delete");
         }
         for(int j=0;j<50;j++){
        	 //System.out.println("good");
        	 c.fwriter().insertBefore("beautiful" + j);
         }
     }
}
