package fr.iambluedev.spartan.api.utils;

import java.io.IOException;
import java.net.ServerSocket;

public class Ports {

	public static Integer getRandomPort() throws IOException{
		ServerSocket socket = new ServerSocket(0);
		socket.close();
		return socket.getLocalPort();
	}
	
}
