package com.team4.skillmarket.admin.inquiry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class inquiryListDao {
	
	/*	
	  	SELECT Q.*, 
       	CASE 
         WHEN A.QUESTION_ANSWER_CONTENT IS NULL 
         THEN 'N' 
         ELSE 'Y' 
       	END AS QUESTION_ANSWER_STATUS,
       	A.QUESTION_ANSWER_NO, A.ADMIN_NO, A.QUESTION_ANSWER_CONTENT , A.QUESTION_ANSWER_ENROLLDATE
		FROM QNA Q
		LEFT JOIN QUESTION_ANSWER A 
		ON Q.QNA_NO = A.QNA_NO;
	 */
	public List<inquiryListVo> selectInquiryList(Connection conn) throws Exception {

		String sql = "SELECT Q.*, CASE WHEN A.QUESTION_ANSWER_CONTENT IS NULL THEN 'N' ELSE 'Y' END AS QUESTION_ANSWER_STATUS, A.QUESTION_ANSWER_NO, A.ADMIN_NO, A.QUESTION_ANSWER_CONTENT , A.QUESTION_ANSWER_ENROLLDATE FROM QNA Q LEFT JOIN QUESTION_ANSWER A ON Q.QNA_NO = A.QNA_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<inquiryListVo> inquiryArrList = new ArrayList<>();
		while(rs.next()) {
			
			String qnaNo = rs.getString("QNA_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String qnaCatNo = rs.getString("QNA_CAT_NO");
			String qnaTitle = rs.getString("QNA_TITLE");
			String qnaContent = rs.getString("QNA_CONTENT");
			String qnaEnrolldate = rs.getString("QNA_ENROLLDATE");
			String qnaStatus = rs.getString("QNA_STATUS");
			String questionAnswerNo = rs.getString("QUESTION_ANSWER_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String questionAnswerContent = rs.getString("QUESTION_ANSWER_CONTENT");
			String questionAnswerEnrolldate = rs.getString("QUESTION_ANSWER_ENROLLDATE");
			
			inquiryListVo ivo = new inquiryListVo();
			ivo.setQnaNo(qnaNo);
			ivo.setMemberNo(memberNo);
			ivo.setQnaCatNo(qnaCatNo);
			ivo.setQnaTitle(qnaTitle);
			ivo.setQnaContent(qnaContent);
			ivo.setQnaEnrolldate(qnaEnrolldate);
			ivo.setQnaStatus(qnaStatus);
			ivo.setQuestionAnswerNo(questionAnswerNo);
			ivo.setAdminNo(adminNo);
			ivo.setQnaNo(qnaNo);
			ivo.setQuestionAnswerContent(questionAnswerContent);
			ivo.setQuestionAnswerEnrolldate(questionAnswerEnrolldate);

			
			inquiryArrList.add(ivo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return inquiryArrList;
	}

	public List<inquiryListVo> selectInquiryCatList(Connection conn) throws Exception {

		String sql = "SELECT * FROM QNA_CATEGORY";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<inquiryListVo> inquiryCatList = new ArrayList<>();
		while (rs.next()) {
			
			String qnaCatNo = rs.getString("QNA_CAT_NO");
			String qnaCatName = rs.getString("QNA_CAT_NAME");
			
			inquiryListVo vo = new inquiryListVo();
			vo.setQnaCatNo(qnaCatNo);
			vo.setQnaCatName(qnaCatName);
			
			inquiryCatList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return inquiryCatList;
	}

	
	
	
}

