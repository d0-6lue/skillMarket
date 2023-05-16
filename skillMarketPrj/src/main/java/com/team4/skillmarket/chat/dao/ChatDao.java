package com.team4.skillmarket.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.team4.skillmarket.chat.vo.ChatRoomSideInfoVo;
import com.team4.skillmarket.chat.vo.ChatVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class ChatDao {

	public int getReceiverByNo(Connection conn, Map<String, String> keyMap) {
		
		int receiverNo = 0;
		
		String getReceiverSql = "SELECT a.member_no AS PARTICIPANT1, b.member_no AS PARTICIPANT2\r\n"
				+ "FROM quotation a\r\n"
				+ "    JOIN (\r\n"
				+ "        SELECT member_no, estimate_no\r\n"
				+ "        FROM estimate a\r\n"
				+ "            JOIN freelancer b ON a.freelancer_no = b.freelancer_no \r\n"
				+ "    ) b on a.estimate_no = b.estimate_no\r\n"
				+ "WHERE quotation_no = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(getReceiverSql);
			pstmt.setString(1, keyMap.get("quotationNo"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String participant1 = rs.getString("PARTICIPANT1");
				String participant2 = rs.getString("PARTICIPANT2");
				
				if( ( keyMap.get("participantNo").equals(participant1) ) ) {
					receiverNo = Integer.parseInt(participant2);
				}
				else if( ( keyMap.get("participantNo").equals(participant2) ) ) {
					receiverNo = Integer.parseInt(participant1);
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
			pstmt.setString(1, receiverKeyMap.get("participantNo"));
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


	public int updateChat(Connection conn, Map<String, String> keyMap, String chatContent) {
		
		int result = 0;
		
		String updateChatSql = "INSERT INTO CHAT_LOG\r\n"
				+ "VALUES (SEQ_CHAT_LOG_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(updateChatSql);
			pstmt.setString(1, keyMap.get("quotationNo"));
			pstmt.setString(2, keyMap.get("participantNo"));
			pstmt.setString(3, chatContent);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	} // sendChat


	public ChatRoomSideInfoVo getSideInfo(Connection conn, String quotationNo) {
		
		ChatRoomSideInfoVo sideInfo = null;
		
		String sql = "SELECT B.MEMBER_NICK AS BUYER, SELLER, ESTIMATE_TITLE, ESTIMATE_THUMBNAIL, ESTIMATE_LINE_INTRODUCTION\r\n"
				+ "FROM QUOTATION A\r\n"
				+ "    JOIN MEMBER B ON A.MEMBER_NO = B.MEMBER_NO\r\n"
				+ "    JOIN (\r\n"
				+ "        SELECT ESTIMATE_NO, SUB.MEMBER_NICK AS SELLER, ESTIMATE_TITLE, ESTIMATE_THUMBNAIL, ESTIMATE_LINE_INTRODUCTION\r\n"
				+ "        FROM ESTIMATE ES\r\n"
				+ "            JOIN (\r\n"
				+ "                SELECT MEMBER_NICK, FREELANCER_NO\r\n"
				+ "                FROM FREELANCER FRE\r\n"
				+ "                    JOIN MEMBER MEM ON FRE.MEMBER_NO = MEM.MEMBER_NO\r\n"
				+ "            ) SUB ON ES.FREELANCER_NO = SUB.FREELANCER_NO \r\n"
				+ "    ) C ON A.ESTIMATE_NO = C.ESTIMATE_NO\r\n"
				+ "WHERE QUOTATION_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, quotationNo);
			rs = pstmt.executeQuery();
			
			if(rs.next() ) {
				sideInfo = new ChatRoomSideInfoVo();
				
				sideInfo.setSeller(rs.getString("SELLER"));
				sideInfo.setTitle(rs.getString("ESTIMATE_TITLE"));
				sideInfo.setThumbnail(rs.getString("ESTIMATE_THUMBNAIL"));
				sideInfo.setLineIntroduce(rs.getString("ESTIMATE_LINE_INTRODUCTION"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return sideInfo;
	} // getSideInfo

}
