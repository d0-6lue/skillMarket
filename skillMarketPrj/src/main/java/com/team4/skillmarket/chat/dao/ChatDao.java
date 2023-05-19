package com.team4.skillmarket.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.team4.skillmarket.chat.vo.ChatRoomSideInfoVo;
import com.team4.skillmarket.chat.vo.ChatVo;
import com.team4.skillmarket.chat.vo.OptionVo;
import com.team4.skillmarket.chat.vo.RequestCategoryVo;
import com.team4.skillmarket.chat.vo.RequestVo;
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
		
		String loadChatSql = "SELECT CHAT_NO, QUOTATION_NO, CHAT_SENDER, MEMBER_NICK, CHAT_CONTENT, CHAT_REQUEST, CHAT_ATTACHMENT, CHAT_READ, CHAT_STATUS, TO_CHAR(CHAT_ENROLL_DATE, 'YYYY/MM/DD HH24:MI') AS CHAT_ENROLL_DATE\r\n"
				+ "FROM ( SELECT CHAT_NO, QUOTATION_NO, CHAT_SENDER, MEMBER_NICK, CHAT_CONTENT, CHAT_REQUEST, CHAT_ATTACHMENT, CHAT_READ, CHAT_STATUS, CHAT_ENROLL_DATE\r\n"
				+ "        FROM CHAT_LOG A\r\n"
				+ "            JOIN MEMBER B ON A.CHAT_SENDER = B.MEMBER_NO\r\n"
				+ "            WHERE QUOTATION_NO = ? AND CHAT_NO > ?\r\n"
				+ "        ORDER BY CHAT_ENROLL_DATE\r\n"
				+ ")";
		
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
				chatVo.setChatSenderNo(rs.getString("CHAT_SENDER"));
				chatVo.setChatSender(rs.getString("MEMBER_NICK"));
				chatVo.setChatContent(rs.getString("CHAT_CONTENT"));
				chatVo.setChatRequest(rs.getString("CHAT_REQUEST"));
				chatVo.setChatAttachment(rs.getString("CHAT_ATTACHMENT"));
				chatVo.setChatRead(rs.getString("CHAT_READ"));
				chatVo.setChatStatus(rs.getString("CHAT_STATUS"));
				chatVo.setChatEnrollDate(rs.getString("CHAT_ENROLL_DATE"));
				
				
				ResultSet rs_ = null;
				if("O".equals(chatVo.getChatRequest())) {
					
					String getReuqtstSql = "SELECT REQUEST_NO, REQUEST_CAT_NO, CHAT_REQUEST_CAT_NAME, REQUEST_CONTENT, REQUEST_ENROLL_DATE, REQUEST_STATUS_NO, OPTION_NO, INPUT_NO\r\n"
							+ "FROM CHAT_REQUEST A\r\n"
							+ "    JOIN CHAT_REQUEST_CAT B ON A.REQUEST_CAT_NO = B.CHAT_REQUEST_CAT_NO\r\n"
							+ "WHERE CHAT_LOG_NO = ?";
					
					pstmt = conn.prepareStatement(getReuqtstSql);
					pstmt.setString(1, chatVo.getChatNo());
					rs_ = pstmt.executeQuery();
					
					if( rs_.next() ) {
						chatVo.setRequestNo(rs_.getString("REQUEST_NO"));
						chatVo.setRequestStatusNo(rs_.getString("REQUEST_STATUS_NO"));
						chatVo.setRequestCatNo(rs_.getString("REQUEST_CAT_NO"));
						chatVo.setRequestCatName(rs_.getString("CHAT_REQUEST_CAT_NAME"));
						chatVo.setOptionNo(rs_.getString("OPTION_NO"));
						chatVo.setInputNo(rs_.getString("INPUT_NO"));
					}
					
				}
				
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


	public int sendChat(Connection conn, Map<String, String> keyMap, String chatContent) {
		
		int result = 0;
		
		String sendChatSql = "INSERT INTO CHAT_LOG\r\n"
				+ "VALUES (SEQ_CHAT_LOG_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(sendChatSql);
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


	public List<RequestCategoryVo> getRequestCat(Connection conn) {
		
		List<RequestCategoryVo> requestCatVoList = new ArrayList<>();
		
		String sql = "SELECT CHAT_REQUEST_CAT_NO, CHAT_REQUEST_CAT_NAME\r\n"
				+ "FROM CHAT_REQUEST_CAT";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				RequestCategoryVo requestCatVo = new RequestCategoryVo();
				
				requestCatVo.setCategoryNo(rs.getString("CHAT_REQUEST_CAT_NO"));
				requestCatVo.setCategoryName(rs.getString("CHAT_REQUEST_CAT_NAME"));
				
				requestCatVoList.add(requestCatVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return requestCatVoList;
	} // getRequestCat


	public List<OptionVo> getOption(Connection conn, String value, String no) {
		
		List<OptionVo> optionList = new ArrayList<>();
		
		
		String sql = "";
		// 300 == 옵션추가 -> 견적서 옵션
		if("300".equals(value)) {
			
			sql = "SELECT ESTIMATE_OPTION_NO, ESTIMATE_NO, ESTIMATE_OPTION_NAME, ESTIMATE_OPTION_PRICE, ESTIMATE_OPTION_PERIOD\r\n"
					+ "FROM ESTIMATE_OPTION\r\n"
					+ "WHERE ESTIMATE_NO = \r\n"
					+ "(SELECT ESTIMATE_NO FROM QUOTATION WHERE QUOTATION_NO = ?)";
		}
		// 400 == 옵션취소 -> 주문소 옵션
		else if("400".equals(value)) {
			
			sql = "SELECT QUOTATION_OPTION_NO, QUOTATION_NO, QUOTATION_OPTION_QUANTITY, B.ESTIMATE_OPTION_NO,\r\n"
					+ "ESTIMATE_NO, ESTIMATE_OPTION_NAME, ESTIMATE_OPTION_PRICE, ESTIMATE_OPTION_PERIOD\r\n"
					+ "FROM QUOTATION_OPTION A\r\n"
					+ "    JOIN ESTIMATE_OPTION B ON A.ESTIMATE_OPTION_NO = B.ESTIMATE_OPTION_NO\r\n"
					+ "WHERE QUOTATION_NO = ?";
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				OptionVo vo = new OptionVo();
				
				vo.setEstimateOptionNo(rs.getString("ESTIMATE_OPTION_NO"));
				vo.setEstimateOptionName(rs.getString("ESTIMATE_OPTION_NAME"));
				vo.setEstimateOptionPrice(rs.getString("ESTIMATE_OPTION_PRICE"));
				vo.setEstimateOptionPeriod(rs.getString("ESTIMATE_OPTION_PERIOD"));
				
				if("400".equals(value)) {
					
					vo.setQuotationOptionNo(rs.getString("QUOTATION_OPTION_NO"));
					vo.setQuotationOptionQuantity(rs.getString("QUOTATION_OPTION_QUANTITY"));
					vo.setQuotationNo(rs.getString("QUOTATION_NO"));
				}
				
				optionList.add(vo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		
		return optionList;
	} // getOption


	public int sendChatR_(Connection conn, Map<String, String> keyMap, RequestVo requestVo) {
		
		int chatSendResult = 0;

		String sendChatSql = "INSERT INTO CHAT_LOG\r\n"
				+ "VALUES (SEQ_CHAT_LOG_NO.NEXTVAL, ?, ?, ?, 'O', DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(sendChatSql);
			pstmt.setString(1, keyMap.get("quotationNo"));
			pstmt.setString(2, keyMap.get("participantNo"));
			pstmt.setString(3, requestVo.getRequestContent());
			chatSendResult = pstmt.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return chatSendResult;
	} // sendChatR_


	public int sendRequest(Connection conn, Map<String, String> keyMap, RequestVo requestVo, String lastChatNo) {
		
		int sendResult = 0;
		
		String sendRequestSql = "INSERT INTO CHAT_REQUEST\r\n"
				+ "( REQUEST_NO, CHAT_LOG_NO, REQUEST_CAT_NO, REQUEST_CONTENT,\r\n"
				+ "REQUEST_ENROLL_DATE, REQUEST_STATUS_NO, OPTION_NO, INPUT_NO )\r\n"
				+ "VALUES ( SEQ_CHAT_REQUEST_NO.NEXTVAL, ?, ?, ?,\r\n"
				+ "DEFAULT, DEFAULT, ?, ?)";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt =conn.prepareStatement(sendRequestSql);
			
			pstmt.setString(1, getLastNo(conn, keyMap.get("quotationNo")) );
			pstmt.setString(2, requestVo.getReuqestCatNo());
			pstmt.setString(3, requestVo.getRequestContent());
			
			pstmt.setString(4, requestVo.getOptionNo());
			pstmt.setString(5, requestVo.getInputNo());
			
			sendResult = pstmt.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return sendResult;
	} // sendRequest
	
	
	public String getLastNo(Connection conn, String quotationNo) {
		
		String result = "";
		
		String getLastNoSql = "SELECT ROWNUM, CHAT_NO, CHAT_ENROLL_DATE\r\n"
				+ "FROM (\r\n"
				+ "    SELECT ROWNUM AS NO, CHAT_NO, CHAT_ENROLL_DATE\r\n"
				+ "    FROM CHAT_LOG\r\n"
				+ "    WHERE QUOTATION_NO = ?\r\n"
				+ "    ORDER BY CHAT_LOG.CHAT_ENROLL_DATE DESC\r\n"
				+ ")\r\n"
				+ "WHERE ROWNUM = 1";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(getLastNoSql);
			pstmt.setString(1, quotationNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getString("CHAT_NO");
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return result;
	}
	
}
