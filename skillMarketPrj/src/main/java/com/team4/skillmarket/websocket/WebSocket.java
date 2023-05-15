package com.team4.skillmarket.websocket;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import com.google.gson.*;
import com.team4.skillmarket.chat.service.ChatService;

@ServerEndpoint("/websocket")
public class WebSocket {

	// WebSocket session 리스트
	private static List<Session> users = Collections.synchronizedList(new ArrayList<>());
	// WebSocket session 맵.
	private static Map<String, Object> userMap = Collections.synchronizedMap(new HashMap<>());
	
	
	// WebSocket 으로 브라우저가 접속하며 요청되는 함수
	@OnOpen
	public void handleOpen(Session session) {
		System.out.println("client is connected .. Session : " + session);
		users.add(session);
	}
	
	
	// WebSocket 으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void handleMessage(String message, Session session) {
		
		System.out.println(message);
		
		// WebSocket 으로 온 메시지를 JSON 으로
		Gson gson = new Gson();
		Map<String, String> messageMap = gson.fromJson(message, Map.class);
		
		
		// message 의 'type' 얻기
		String type = String.valueOf(messageMap.get("type"));
		
		String senderNo = String.valueOf(messageMap.get("sender"));
		String quotationNo = String.valueOf(messageMap.get("roomNo"));
		
		if(type != null && "regist".equals(type)) {
			
			ChatService chatService = new ChatService();
			chatService.getRecieverByNo(senderNo, quotationNo);
			
			
		}
		else if(type != null && "".equals(type)) {
			
			
			
		}
	}
	
}
