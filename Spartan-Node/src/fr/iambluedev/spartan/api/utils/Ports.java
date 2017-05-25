package fr.iambluedev.spartan.api.utils;

import java.io.IOException;
import java.net.ServerSocket;

public class Ports {

	public static Integer getRandomPort() throws IOException{
		ServerSocket socket = new ServerSocket(0);
		socket.close();
		return socket.getLocalPort();
	}
	
	public static boolean isAvailable(Integer port){
		try {
			ServerSocket socket = new ServerSocket(port);
			socket.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
}
