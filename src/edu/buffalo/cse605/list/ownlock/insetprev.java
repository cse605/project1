package edu.buffalo.cse605.list.ownlock;

import edu.buffalo.cse605.list.Cursor;

public class insetprev extends Thread{
	  private CursorRW<String> c;
	     private int els;

	     public insetprev(CursorRW<String> c, int els){
	    	 this.els = els;
	    	 this.c = c;
	     }
	     
	    @Override
		public void run() {
	    	long startTime = System.currentTimeMillis();
	    	 for(int j=0; j < els; j++){
	        	 c.writer().insertBefore("beautiful" + j);
	    		 //c.writer().delete();
	        	 System.out.println("success" +j+" "+Thread.currentThread().getId());
	         }
	    	 long endtime=System.currentTimeMillis();
	    	 long total=endtime-startTime;
	    	 System.out.println("the time of "+Thread.currentThread().getId()+" "+total);
	     }
}
