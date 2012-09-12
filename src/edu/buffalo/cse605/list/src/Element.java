package edu.buffalo.cse605.list.src;

import edu.buffalo.cse605.list.iface.IElement;

public class Element<T> implements IElement<T> {
    private T val;
    private Element<T> next, prev;
    
    // Constructor
    public Element(T val) {
    	this(val, null, null);
    }

    // Constructor
    public Element(T val, Element<T> prev, Element<T> next) {
    	if ( val == null ) {
    		// Throw Exception
    		throw new Error("can`t create element");
    	}
    	// Assign Value
    	this.val = val;
    	// Assign Prev and Next
    	if ( prev != null && next != null ) {
	    	this.prev = prev;
	    	this.next = next;
    	} else if ( prev == null && next == null ) {
    		this.prev = this;
    		this.next = this;
    	}
      
    }
    
    // GET next Element
    protected Element<T> next() {
    	return this.next;
    }
    
    // GET prev Element
    protected Element<T> prev() {
    	return this.prev;
    }
    
    // Add an element after this element
    protected boolean addAfter(Element<T> el) {
    	
             el.next=this.next;
             el.prev=this;
             if(this.prev.prev==this){//having two elements
            	 this.prev.prev=el;
            	 this.next=el;
             }
             else if(this.prev==this){//having one elements
            	 this.prev=el;
            	 this.next=el;
            	 
             }
             else{
            	 this.next.prev=el;
            	 this.next=el;
             }
    	
    	//this.next = el;
    	//el.prev = this;
    	return true;
    }
    
    // Add an element before this element
    protected boolean addBefore(Element<T> el) {
    	    el.next = this;
    		el.prev=this.prev;
    		if(this.next==this){
    			this.prev=el;
    			this.next=el;
    		}
    		else if(this.next.next==this){
    			this.next.next=el;
    			this.prev=el;
    		}
    		else{
    			this.prev=el;
    			this.prev.prev=el;
    		}
    	
    	
    
    	return true;
    }
     
    protected boolean delete(){
    		System.out.println("delete "+this.val);
    		 if(this.next==this.prev && this.next!=this){//two elements in the list
    			this.next.next=this.prev;
    			this.next.prev=this.prev;
    			
    		}
    		else if(this.prev==this.next.next){//three elements in the list
    			this.next.prev=this.prev;
    			this.prev.next=this.next;
    			
    		}

    		else{this.next.prev=this.prev;
    		this.prev.next=this.next;
    		
    		}

    		
    		return true;
    		   }
    
    // Helper
    public String toString() {
    	return "v:" + this.val + ",prev:" + this.prev.val + ",next:" + this.next.val;
    }

	@Override
	public T value() {
		return this.val;
	}
  }