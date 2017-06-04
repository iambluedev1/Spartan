package fr.iambluedev.spartan.api.utils;

import fr.iambluedev.spartan.api.gson.JSONObject;
import fr.iambluedev.spartan.node.Main;

public class RedisJsonMessage {

	private JSONObject jsonObj;
	
	@SuppressWarnings("unchecked")
	public RedisJsonMessage(){
		this.jsonObj = new JSONObject();
		this.jsonObj.put("id", Main.getInstance().getName() + "-" + Main.getInstance().getId());
	}
	
	@SuppressWarnings("unchecked")
	public RedisJsonMessage setCmd(String cmd){
		this.jsonObj.put("cmd", cmd);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public RedisJsonMessage setContent(String content){
		this.jsonObj.put("content", content);
		return this;
	}
	
	public String get(){
		return this.jsonObj.toJSONString();
	}
}
