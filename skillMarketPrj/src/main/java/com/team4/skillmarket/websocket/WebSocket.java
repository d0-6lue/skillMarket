package com.team4.skillmarket.websocket;

import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import com.google.gson.*;
import com.team4.skillmarket.chat.service.ChatService;
import com.team4.skillmarket.chat.vo.ChatVo;
import com.team4.skillmarket.chat.vo.RequestVo;

@ServerEndpoint("/websocket")
public class WebSocket {

	// WebSocket session 리스트
	private static List<Session> users = Collections.synchronizedList(new ArrayList<>());
	// WebSocket session 맵.
	private static Map<String, Object> usersMap = Collections.synchronizedMap(new HashMap<>());
	
	
	// WebSocket 으로 브라우저가 접속하며 요청되는 함수
	@OnOpen
	public void handleOpen(Session session) {
		System.out.println("client is connected .. Session : " + session);
		users.add(session);
	}
	
	
	// WebSocket 으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void handleMessage(String message, Session session) {
		
		// ChatService
		ChatService chatService = new ChatService();
		
		
		// message 확인용
		System.out.println("message = " + message);
		
		
		// WebSocket 으로 온 message 를 JSON 으로
		Gson gson = new Gson();
		Map<String, String> messageMap = gson.fromJson(message, Map.class);
		
		
		// message 의 'type' 얻기
		String type = String.valueOf(messageMap.get("type"));
		
		// 보낸사람(senderNo)랑 주문서번호(roomNo)
		String participantNo = String.valueOf(messageMap.get("senderNo"));
		String quotationNo = String.valueOf(messageMap.get("roomNo"));
		
		// 보낸사람과 주문서번호를 키값으로
		Map<String, String> keyMap = new HashMap<>();
		keyMap.put("participantNo", participantNo);
		keyMap.put("quotationNo", quotationNo);
		
		// 받는사람 번호 가져오기
		Map<String, String> receiverKeyMap = new HashMap<>();
		int receiverNo = chatService.getReceiverByNo(keyMap);
		
		if( receiverNo == 0 ) {
			// 오류
		}
		else {
			// 받는사람의 session 키값
			receiverKeyMap.put("participantNo", Integer.toString(receiverNo));
			receiverKeyMap.put("quotationNo", quotationNo);
		}
		
		
		//---------------------------------------------------------------------------------------------------------------------------
		
		
		// type 이 'regist' 일 경우 ----------------------------------------------------------------------------------------------------
		if(type != null && "regist".equals(type)) {
			
			// Map<String, Object> usersMap 에 세션값 저장.
			usersMap.put(keyMap.toString(), session);
			
		}
		// type 이 'loadChat' 일 경우 ---------------------------------------------------------------------------------------------------
		else if(type != null && "loadChat".equals(type)) {
			
			// 읽음 처리
			int result = chatService.checkRead(receiverKeyMap);
			
			if(result == 0) {
				// 오류
			}
			
			
			// 채팅 리스트 가져오기
			List<ChatVo> chatList = new ArrayList<>();
			chatList = chatService.loadChat(quotationNo, "0");
			
			
			// Map 으로 담기
			Map<String, Object> replyMessageMap = new HashMap<>();
			replyMessageMap.put("type", "load");
			replyMessageMap.put("chatList", chatList);
			
			// JSON 으로 변환
			String reply_msg = gson.toJson(replyMessageMap);
			
			// Session 얻기
			Session s = (Session) usersMap.get(keyMap.toString());
			
			try {
				// 채팅리스트 클라이언트로 보내기
				s.getBasicRemote().sendText(reply_msg);
				
				// 상대가 접속중이라면 상대의 채팅리스트 다시 보내기
				Session r = (Session) usersMap.get(receiverKeyMap.toString());
				System.out.println(r);
				if( r!= null && r.isOpen() ) {
					
					// 채팅 리스트 가져오기
					chatList = new ArrayList<>();
					chatList = chatService.loadChat(quotationNo, "0");
					
					// Map 으로 담기
					replyMessageMap = new HashMap<>();
					replyMessageMap.put("type", "load");
					replyMessageMap.put("chatList", chatList);
					
					// JSON 으로 변환
					reply_msg = gson.toJson(replyMessageMap);
					
					r.getBasicRemote().sendText(reply_msg);
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		// type 이 'sendChat' 일 경우 ---------------------------------------------------------------------------------------------------
		else if( type != null && "sendChat".equals(type)) {
			// Session 얻기
			Session s = (Session) usersMap.get(keyMap.toString());
			Session r = (Session) usersMap.get(receiverKeyMap.toString());
			
			String chatContent = String.valueOf(messageMap.get("content"));
			String lastNo = String.valueOf(messageMap.get("lastChatNo"));
			
			int sendResult = chatService.sendChat(keyMap, chatContent, lastNo);
			
			List<ChatVo> chatList = new ArrayList<>();
			if( r!= null && r.isOpen() ) {
				int result = chatService.checkRead(keyMap);
			}
			chatList = chatService.loadChat(quotationNo, lastNo);
			
			// Map 으로 담기
			Map<String, Object> replyMessageMap = new HashMap<>();
			replyMessageMap.put("type", "add");
			replyMessageMap.put("chatList", chatList);
			
			// JSON 으로 변환
			String reply_msg = gson.toJson(replyMessageMap);
			
			
			try {
				// 채팅리스트 클라이언트로 보내기
				s.getBasicRemote().sendText(reply_msg);
				
				// 상대가 접속중이라면 상대의 채팅리스트 다시 보내기
				
				System.out.println(r);
				if( r!= null && r.isOpen() ) {
					
					// 채팅 리스트 가져오기
					chatList = new ArrayList<>();
					chatList = chatService.loadChat(quotationNo, lastNo);
					
					// Map 으로 담기
					replyMessageMap = new HashMap<>();
					replyMessageMap.put("type", "add");
					replyMessageMap.put("chatList", chatList);
					
					// JSON 으로 변환
					reply_msg = gson.toJson(replyMessageMap);
					
					r.getBasicRemote().sendText(reply_msg);
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		// type 이 'request' 일 경우 ---------------------------------------------------------------------------------------------------
		else if( type != null && "request".equals(type)) {
			// Session 얻기
			Session s = (Session) usersMap.get(keyMap.toString());
			Session r = (Session) usersMap.get(receiverKeyMap.toString());
			
			String requestContent = String.valueOf(messageMap.get("content"));
			String lastNo = String.valueOf(messageMap.get("lastChatNo"));
			
			RequestVo requestVo = new RequestVo();
			String requestCatNo = messageMap.get("categoryNo");
			requestVo.setReuqestCatNo(requestCatNo);
			requestVo.setRequestContent(requestContent);
			
			if("300".equals(requestCatNo)) {
				requestVo.setOptionNo(messageMap.get("addOptionNo"));
				requestVo.setInputNo(messageMap.get("quantity"));
			}
			else if("400".equals(requestCatNo)) {
				requestVo.setOptionNo("0");
				requestVo.setOptionNo(messageMap.get("deleteOptionNo"));
			}
			else if("500".equals(requestCatNo)) {
				requestVo.setOptionNo("0");
				requestVo.setInputNo(messageMap.get("period"));
			}
			else if("600".equals(requestCatNo)) {
				requestVo.setOptionNo("0");
				requestVo.setInputNo(messageMap.get("period"));
			}
			else if("800".equals(requestCatNo)) {
				requestVo.setOptionNo("0");
				requestVo.setInputNo(messageMap.get("handsel"));
			}
			else {
				requestVo.setOptionNo("0");
				requestVo.setInputNo("0");
			}
			
			chatService.sendRequest(keyMap, requestVo, lastNo);
			
			List<ChatVo> chatList = new ArrayList<>();
//			if( r!= null && r.isOpen() ) {
//				int result = chatService.checkRead(keyMap);
//			}
			chatList = chatService.loadChat(quotationNo, lastNo);
			
			
		}
		// type 이 '' 일 경우 ---------------------------------------------------------------------------------------------------
		
		
	}
	
	
	// WebSocket 과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void handleClose(Session session) {
		System.out.println("client is disconnected .. Session : " + session);
		users.remove(session);
		usersMap.remove(session);
	}
	
	
	// WebSocket 과 브라우저 간에 통신 에러가 발생하면 요청되는 함수
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
	
}
