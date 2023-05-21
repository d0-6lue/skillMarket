package com.team4.skillmarket.admin.FAQ.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.common.db.JDBCTemplate;


public class AdminFAQDao {

	public List<AdminFAQVo> selectFAQList(Connection conn) throws Exception {
		

		String slq = "SELECT * FROM FAQ ORDER BY FAQ_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(slq);
		ResultSet rs = pstmt.executeQuery();
		
		List<AdminFAQVo> FAQArrList = new ArrayList<>();
		while (rs.next()) {
			
			String faqNo = rs.getString("FAQ_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String faqCatNo = rs.getString("FAQ_CAT_NO");
			String faqTitle = rs.getString("FAQ_TITLE");
			String faqQContent = rs.getString("FAQ_Q_CONTENT");
			String faqAContent = rs.getString("FAQ_A_CONTENT");
			String faqStatus = rs.getString("FAQ_STATUS");
			String faqHit = rs.getString("FAQ_HIT");
			String faqEnrolldate = rs.getString("FAQ_ENROLLDATE");
			String faqModifydate = rs.getString("FAQ_MODIFYDATE");
			
			
			AdminFAQVo vo = new AdminFAQVo();
			vo.setFaqNo(faqNo);
			vo.setAdminNo(adminNo);
			vo.setFaqCatNo(faqCatNo);
			vo.setFaqTitle(faqTitle);
			vo.setFaqQContent(faqQContent);
			vo.setFaqAContent(faqAContent);
			vo.setFaqStatus(faqStatus);
			vo.setFaqHit(faqHit);
			vo.setFaqEnrolldate(faqEnrolldate);
			vo.setFaqModifydate(faqModifydate);
			
			FAQArrList.add(vo);
			
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return FAQArrList;
	}

}
