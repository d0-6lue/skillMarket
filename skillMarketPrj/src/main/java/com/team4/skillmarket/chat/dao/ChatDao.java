package com.team4.skillmarket.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.team4.skillmarket.chat.vo.ChatVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class ChatDao {

	public int getReceiverByNo(Connection conn, Map<String, String> keyMap) {
		
		int receiverNo = 0;
		
		String getReceiverSql = "SELECT CHAT_SENDER\r\n"
				+ "FROM CHAT_LOG\r\n"
				+ "WHERE QUOTATION_NO = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(getReceiverSql);
			pstmt.setString(1, keyMap.get("quotationNo"));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String chatSender = rs.getString("CHAT_SENDER");
				
				if( !( keyMap.get("senderNo").equals(chatSender) ) ) {
					receiverNo = Integer.parseInt(chatSender);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return receiverNo;
	} // getReceiverByNo

	
	public int checkRead(Connection conn, Map<String, String> receiverKeyMap) {
		
		int result = 0;
		
		String checkReadSql = "UPDATE CHAT_LOG SET CHAT_READ = 'O' WHERE CHAT_SENDER = ? AND QUOTATION_NO = ? AND CHAT_READ = 'X'";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(checkReadSql);
			pstmt.setString(1, receiverKeyMap.get("receiverNo"));
			pstmt.setString(2, receiverKeyMap.get("quotationNo"));
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	} // checkRead


	public List<ChatVo> loadChat(Connection conn, String quotationNo, String lastChatNo) {
		
		List<ChatVo> chatList = new ArrayList<>();
		
		String loadChatSql = "SELECT CHAT_NO, QUOTATION_NO, CHAT_SENDER, CHAT_CONTENT, CHAT_REQUEST, CHAT_ATTACHMENT, CHAT_READ, CHAT_STATUS, TO_CHAR(CHAT_ENROLL_DATE, 'YYYY/MM/DD HH24:MI') AS CHAT_ENROLL_DATE\r\n"
				+ "FROM CHAT_LOG\r\n"
				+ "WHERE QUOTATION_NO = ? AND CHAT_NO > ?\r\n"
				+ "ORDER BY CHAT_LOG.CHAT_ENROLL_DATE";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(loadChatSql);
			pstmt.setString(1, quotationNo);
			pstmt.setString(2, lastChatNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ChatVo chatVo = new ChatVo();
				
				chatVo.setChatNo(rs.getString("CHAT_NO"));
				chatVo.setQuotationNo(rs.getString("QUOTATION_NO"));
				chatVo.setChatSender(rs.getString("CHAT_SENDER"));
				chatVo.setChatContent(rs.getString("CHAT_CONTENT"));
				chatVo.setChatRequest(rs.getString("CHAT_REQUEST"));
				chatVo.setChatAttachment(rs.getString("CHAT_ATTACHMENT"));
				chatVo.setChatRead(rs.getString("CHAT_READ"));
				chatVo.setChatStatus(rs.getString("CHAT_STATUS"));
				chatVo.setChatEnrollDate(rs.getString("CHAT_ENROLL_DATE"));
				
				chatList.add(chatVo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return chatList;
	} // loadChat

}
