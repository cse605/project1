package edu.buffalo.cse605.list.ownlock;

public class mainRW {
	
	
    public static void main(String[] args){
    	
    	
    	FDListRW<String> f=  new FDListRW<String>("hi");
    	CursorRW<String> c1=f.reader(f.head());
    	CursorRW<String> c2=f.reader(f.head());
    	Thread a1=new insetprev(c1,10000);
    	Thread a2=new insetprev(c2,10000);
    	a1.start();
    	a2.start();
    }
}
