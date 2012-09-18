package edu.buffalo.cse605.list;

import edu.buffalo.cse605.list.iface.IElement;

public class Element<T> implements IElement<T> {
    private T val;
    protected Element<T> next;
	protected Element<T> prev;
    
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
    public Element<T> next() {
    	return this.next;
    }
    
    // GET prev Element
    public Element<T> prev() {
    	return this.prev;
    }
    
    // Add an element after this element
    public boolean addAfter(Element<T> el) {
    	el.next = this.next;
    	el.prev = this;
    	
    	if(this.prev == this) { // 1 elements
    		this.prev = el;
    		this.next = el;
    	} else if(this.prev.prev == this) { // 2 elements
    		this.prev.prev = el;
    		this.next = el;
    	} else {
    		this.next.prev = el;
    		this.next = el;
    	}
    	return true;
    }
    
    // Add an element before this element
    public boolean addBefore(Element<T> el) {
	    el.next = this;
		el.prev = this.prev;
		
		if(this.next == this) { // 1 element
			this.prev = el;
			this.next = el;
		} else if (this.next.next == this) { // 2 elements
			this.next.next = el;
			this.prev = el;
		} else {
			this.prev.next = el;
			this.prev = el;
		}
    	return true;
    }
    
    public boolean delete() {
    	if(this.next == this.prev && this.next != this){//two elements in the list
    		this.next.next = this.prev;
    		this.next.prev = this.prev;
    	} else if(this.prev == this.next.next) {//three elements in the list
			this.next.prev = this.prev;
			this.prev.next = this.next;
		} else {
			this.next.prev = this.prev;
			this.prev.next = this.next;
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