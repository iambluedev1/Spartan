package fr.iambluedev.spartan.api.utils;

public interface Callback<V> {
	
	public abstract void done(V object, Throwable execption);
	
}
