package fr.iambluedev.spartan.api.utils;

import java.io.File;

public class IOUtils {

	public static void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
	
}
