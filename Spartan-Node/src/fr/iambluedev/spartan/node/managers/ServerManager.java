package fr.iambluedev.spartan.node.managers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import fr.iambluedev.spartan.api.node.SpartanNode;
import fr.iambluedev.spartan.api.server.SpartanServer;
import fr.iambluedev.spartan.api.server.SpartanServers;
import fr.iambluedev.spartan.api.utils.IOUtils;

public class ServerManager extends SpartanServers{

	private Map<String, SpartanServer> servers;
	private File folder;
	private SpartanNode instance;
	
	public ServerManager(SpartanNode instance){
		this.servers = new HashMap<String, SpartanServer>();
		this.folder = new File("servers");
		this.instance = instance;
		if(this.folder.exists()){
			this.instance.getLogger().log(Level.INFO, "Deleting servers folder");
			IOUtils.deleteDir(this.folder);
		}
		
		this.folder.mkdir();
	}
	
	@Override
	public Map<String, SpartanServer> getServers() {
		return this.servers;
	}
	
	@Override
	public void clearServers() {
		this.servers.clear();
	}

	@Override
	public SpartanServer getServer(String name) {
		if(this.servers.containsKey(name)){
			return this.servers.get(name);
		}else{
			return null;
		}
	}
	
	@Override
	public void addServer(String name, SpartanServer server) {
		if(!this.servers.containsKey(name)){
			this.servers.put(name, server);
		}
	}

	public File getFolder() {
		return this.folder;
	}

	public SpartanNode getInstance() {
		return this.instance;
	}

	@Override
	public void removeServer(String name) {
		if(this.servers.containsKey(name)){
			this.servers.remove(name);
		}
	}
	
}
