package com.team4.skillmarket.chat.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.team4.skillmarket.chat.dao.ChatDao;
import com.team4.skillmarket.chat.vo.ChatVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class ChatService {
	
	private final ChatDao chatDao = new ChatDao();

	public int getReceiverByNo(Map<String, String> keyMap) {
		
		int receiverNo = 0;
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		receiverNo = chatDao.getReceiverByNo(conn, keyMap);
		
		JDBCTemplate.close(conn);
		
		return receiverNo;
	} // getReceiverByNo

	
	public int checkRead(Map<String, String> receiverKeyMap) {
		
		int result = 0;
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		result = chatDao.checkRead(conn, receiverKeyMap);
		
		if(result != 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	} // checkRead


	public List<ChatVo> loadChat(String quotationNo, String lastChatNo) {
		
		List<ChatVo> chatList = null;
		
		Connection conn = JDBCTemplate.getConnection();
		
		chatList = chatDao.loadChat(conn, quotationNo, lastChatNo);
		
		JDBCTemplate.close(conn);
		
		return chatList;
	} // loadChat


	public List<ChatVo> sendChat(Map<String, String> keyMap, String chatContent, String lastNo) {
		
		
		
		return null;
	} // sendChat

}
