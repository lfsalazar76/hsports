package com.lfsalazar.jsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/socket/help")
public class HelpServerSocketEndpoint {

	private static List<Session> sessions = new ArrayList<>();
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connect to the client: ");
		sessions.add(session);
	}
	
	@OnMessage
	public void onMessage(String message,Session session) {
		
		sessions.stream().forEach(s->{
			try {
				s.getBasicRemote().sendText(message);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		});
		
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection was closed");
	}
}
