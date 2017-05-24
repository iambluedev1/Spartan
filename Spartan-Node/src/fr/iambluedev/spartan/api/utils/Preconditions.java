package fr.iambluedev.spartan.api.utils;

public class Preconditions {

	public static <T> T checkNotNull(T reference, Object errorMessage){
		if (reference == null) {
			throw new NullPointerException(String.valueOf(errorMessage));
	    }
    	return reference;
	}
	
	public static void checkArgument(boolean expression, Object errorMessage){
		if (!expression) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
	    }
	}
}
