package fr.iambluedev.logger;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;

public class StringFormatter {

	private String msg;
	
	public StringFormatter(String msg){
		this.setMsg(msg);
	}
	
	public StringFormatter removeColors(){
		for(PrintColor color : PrintColor.values()){
			if(this.getMsg().contains(color.getAnsiColor())){
				this.setMsg(this.getMsg().replace(color.getAnsiColor(), ""));
			}
		}
		return this;
	}
	
	public StringFormatter removeSymbol(){
		for(PrintSymbol symbol : PrintSymbol.values()){
			if(this.getMsg().contains(symbol.getUnicode())){
				this.setMsg(this.getMsg().replace(symbol.getUnicode(), ""));
			}
		}
		return this;
	}
	
	public StringFormatter toSlug(){
		String nowhitespace = Pattern.compile("[\\s]").matcher(this.getMsg()).replaceAll("");  
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);  
	    this.setMsg(Pattern.compile("[^\\w-]").matcher(normalized).replaceAll(""));
	    return this;
	}
	
	public String getMsg(){
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}