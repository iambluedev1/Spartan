package fr.iambluedev.spartan.api.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.api.gson.parser.JSONParser;
import fr.iambluedev.spartan.api.gson.parser.ParseException;
import fr.iambluedev.spartan.api.node.SpartanNode;

public abstract class SpartanConfig {

	private String name;
	private File file;
	private File folder;
	private JSONObject jsonObject;
	private SpartanNode instance;
	
	public SpartanConfig(String name, SpartanNode instance){
		this.name = name;
		this.instance = instance;
		this.folder = new File("configs");
		this.file = new File(folder, this.name + ".json");
		
		if(!this.folder.exists()){
			this.folder.mkdir();
		}
		
		JSONParser jsonParser = new JSONParser();
		
		if(!this.file.exists()){
			this.jsonObject = new JSONObject();
			try {
				this.file.createNewFile();
				this.setupConfig();
			} catch (IOException e) {
				this.instance.getLogger().log(Level.SEVERE, "Error when creating the " + this.getName() + " file");
				e.printStackTrace();
			}
		}else{
			try(BufferedReader br = new BufferedReader(new FileReader(this.file))) {
				this.instance.getLogger().log(Level.INFO, "Loading " + this.getName() + " file");
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();
			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    this.jsonObject = (JSONObject) jsonParser.parse(sb.toString());
			} catch (IOException | ParseException e1) {
				this.instance.getLogger().log(Level.SEVERE, "Error when loading the " + this.getName() + " file");
				e1.printStackTrace();
			} 
		}
	}
	
	public abstract void setupConfig();

	public String getName() {
		return this.name;
	}

	public File getFile() {
		return this.file;
	}

	public File getFolder() {
		return this.folder;
	}

	public JSONObject getJsonObject() {
		return this.jsonObject;
	}
	
	public void save(){
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.file), "utf-8"))) {
			writer.write(this.jsonObject.toJSONString());
		} catch (UnsupportedEncodingException e) {
			this.instance.getLogger().log(Level.SEVERE, "Error when saving the " + this.getName() + " file");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			this.instance.getLogger().log(Level.SEVERE, "Error when saving the " + this.getName() + " file");
			e.printStackTrace();
		} catch (IOException e) {
			this.instance.getLogger().log(Level.SEVERE, "Error when saving the " + this.getName() + " file");
			e.printStackTrace();
		}
	}

	public SpartanNode getInstance() {
		return this.instance;
	}
}
