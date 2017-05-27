package fr.iambluedev.spartan.api.download;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import javax.net.ssl.HttpsURLConnection;

import fr.iambluedev.spartan.api.http.SpartanUrl;
import fr.iambluedev.spartan.api.node.SpartanNode;

public class SpartanDownload extends Thread{

	private SpartanUrl spartanUrl;
	private SpartanNode instance;
	
	public SpartanDownload(SpartanUrl spartanUrl, SpartanNode instance){
		this.spartanUrl = spartanUrl;
		this.instance = instance;
	}
	
	@Override
	public void run(){
		URL url = null;
		try {
			url = new URL(this.getSpartanUrl().getUrl());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
        HttpsURLConnection httpConn = null;
		try {
			httpConn = (HttpsURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
        int responseCode = 0;
        
		try {
			responseCode = httpConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
        if (responseCode == HttpURLConnection.HTTP_OK) {
        	this.getInstance().getLogger().log(Level.INFO, "[" + this.getSpartanUrl().getName() + "]  Downloading zipFile");
            InputStream inputStream = null;
			try {
				inputStream = httpConn.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
            String saveFilePath = this.getSpartanUrl().getDest();
             
            FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(saveFilePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
 
            int bytesRead = -1;
            byte[] buffer = new byte[8192];
            
            try {
				while ((bytesRead = inputStream.read(buffer)) != -1) {
				    outputStream.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
 
            try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            this.getInstance().getLogger().log(Level.INFO, "[" + this.getSpartanUrl().getName() + "] File downloaded to : " + this.getSpartanUrl().getDest());
        } else {
        	this.getInstance().getLogger().log(Level.WARNING, "[" + this.getSpartanUrl().getName() + "] Error : Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
	}	
	
	public SpartanUrl getSpartanUrl() {
		return this.spartanUrl;
	}

	public SpartanNode getInstance() {
		return this.instance;
	}
}
