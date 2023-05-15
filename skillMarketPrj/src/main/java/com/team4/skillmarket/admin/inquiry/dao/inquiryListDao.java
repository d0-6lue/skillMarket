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

	public List<inquiryListVo> selectInquiryList(Connection conn) throws Exception {

		String sql = "SELECT * FROM QNA";
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
			
			inquiryListVo ivo = new inquiryListVo();
			ivo.setQnaNo(qnaNo);
			ivo.setMemberNo(memberNo);
			ivo.setQnaCatNo(qnaCatNo);
			ivo.setQnaTitle(qnaTitle);
			ivo.setQnaContent(qnaContent);
			ivo.setQnaEnrolldate(qnaEnrolldate);
			ivo.setQnaStatus(qnaStatus);

			
			inquiryArrList.add(ivo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return inquiryArrList;
	}


}

