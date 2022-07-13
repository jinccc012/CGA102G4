package com.scar.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/front_end/{mebNo}")
public class ScarWebSocket {
	
private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	
	@OnOpen
	public void onOpen(@PathParam("mebNo") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);
//		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		for (Session session : connectedSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(message);
		}
//		System.out.println("Message received: " + message);
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
//		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
//				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
	}
}
