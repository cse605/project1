package edu.buffalo.cse605.list.iface;

public interface IWriter<T> {
	public boolean insertBefore(T val);
	public boolean insertAfter(T val);
	public boolean delete();
}
