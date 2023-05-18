package com.team4.skillmarket.chat.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.team4.skillmarket.chat.dao.ChatDao;
import com.team4.skillmarket.chat.vo.ChatRoomSideInfoVo;
import com.team4.skillmarket.chat.vo.ChatVo;
import com.team4.skillmarket.chat.vo.OptionVo;
import com.team4.skillmarket.chat.vo.RequestCategoryVo;
import com.team4.skillmarket.chat.vo.RequestVo;
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


	public int sendChat(Map<String, String> keyMap, String chatContent, String lastNo) {
		
		int result = 0;
		
		Connection conn = JDBCTemplate.getConnection();
		
		result = chatDao.sendChat(conn, keyMap, chatContent);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	} // sendChat


	public ChatRoomSideInfoVo getSideInfo(String quotationNo) {
		
		ChatRoomSideInfoVo sideInfo = null;
		
		Connection conn = JDBCTemplate.getConnection();
		
		sideInfo = chatDao.getSideInfo(conn, quotationNo);
		
		JDBCTemplate.close(conn);
		
		return sideInfo;
	} // getSideInfo


	public List<RequestCategoryVo> getRequestCat() {
		
		List<RequestCategoryVo> requestCatVoList = null;
		
		Connection conn = JDBCTemplate.getConnection();
		
		requestCatVoList = chatDao.getRequestCat(conn);
		
		JDBCTemplate.close(conn);
		
		return requestCatVoList;
	} // getRequestCat


	public List<OptionVo> getOption(String value, String no) {
		
		List<OptionVo> optionList = null;
		
		Connection conn = JDBCTemplate.getConnection();
		
		optionList = chatDao.getOption(conn, value, no);
		
		JDBCTemplate.close(conn);
		
		return optionList;
	} // getOption


	public int sendRequest(Map<String, String> keyMap, RequestVo requestVo, String lastChatNo) {
		
		int result = 0;
		
		Connection conn = JDBCTemplate.getConnection();
		
		result = chatDao.sendChatR_(conn, keyMap, requestVo);
		
		if(result == 1) {
			
			result = chatDao.sendRequest(conn, keyMap, requestVo, lastChatNo);
			
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
			}
			
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
