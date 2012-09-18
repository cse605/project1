package edu.buffalo.cse605.coarselist.src;


public class coarseMain {
	
   public static void main(String[] args){
	   coarseFDList<String> f;
		coarseCursor<String> c;
		
		f = new coarseFDList<String> ("hi");
		c = f.creader( f.head() );
		System.out.println("current element pointed by cursor..." + c.curr().toString());
		c.next();
		System.out.println("head in list..." + f.head().value());
		c.cwriter().insertBefore("hi2");
	       
        c.cwriter().insertAfter("good");
        
        c.cwriter().insertAfter("bad");
        c.cwriter().delete();
        c.cwriter().delete();
        c.cwriter().delete();
        c.cwriter().delete();
   }
   
}
