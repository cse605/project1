package edu.buffalo.cse605.finelist.src;

import java.io.IOException;


public class fineMain {
	
	
	public static void main(String[] args){
		   fineFDList<String> f;		
		   f = new fineFDList<String> ("hi");	
		   testThread test1=new testThread(f);
		   testThread2 test2=new testThread2(f);
		   testThread2 test3=new testThread2(f);
		   test1.start();
		   
		   test2.start();
		   test3.start();
			
	        
	   }
}
