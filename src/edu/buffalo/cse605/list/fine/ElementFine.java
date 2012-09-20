package edu.buffalo.cse605.list.fine;

import java.util.concurrent.locks.*;

import edu.buffalo.cse605.list.Element;


public class ElementFine<T> extends Element<T> {
    public final ReentrantLock nextlock = new ReentrantLock();
    public final ReentrantLock prevlock = new ReentrantLock();
    
    // Constructor
    public ElementFine(T val) {
    	this(val, null, null);
    }
    // Constructor
    public ElementFine(T val, ElementFine<T> prev, ElementFine<T> next) {
    	super(val, prev, next);
    }
    
    // GET next Element
    public ElementFine<T> next() {
    	return (ElementFine<T>) this.next;
    }
    
    // GET prev Element
    public ElementFine<T> prev() {
    	return (ElementFine<T>) this.prev;
    }
}
