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
    	this.next = el;
    	el.prev = this;
    	return true;
    }
    
    // Add an element before this element
    protected boolean addBefore(Element<T> el) {
    	this.prev = el;
    	el.next = this;
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