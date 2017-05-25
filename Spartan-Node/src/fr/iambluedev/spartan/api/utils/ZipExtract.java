package fr.iambluedev.spartan.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtract {

	public static void extract(String file, String dest){
	     byte[] buffer = new byte[1024];
	     try{
	    	File folder = new File(dest);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}

	    	ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
	    	ZipEntry ze = zis.getNextEntry();

	    	while(ze!=null){
	    		String fileName = ze.getName();
	           	File newFile = new File(dest + File.separator + fileName);
	            new File(newFile.getParent()).mkdirs();

	            FileOutputStream fos = new FileOutputStream(newFile);

	            int len;
            	while ((len = zis.read(buffer)) > 0) {
            		fos.write(buffer, 0, len);
	            }

	            fos.close();
	            ze = zis.getNextEntry();
	    	}

	        zis.closeEntry();
	    	zis.close();
	    }catch(IOException ex){
	       ex.printStackTrace();
	    }		
	}
	
}
