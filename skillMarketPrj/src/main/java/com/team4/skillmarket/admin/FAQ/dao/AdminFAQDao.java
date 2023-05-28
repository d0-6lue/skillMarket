package com.team4.skillmarket.admin.FAQ.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.common.db.JDBCTemplate;


public class AdminFAQDao {

	public List<AdminFAQVo> selectFAQList(Connection conn) throws Exception {
		

		String slq = "SELECT FAQ.*, FAQ_CATEGORY.FAQ_CAT_NAME FROM FAQ JOIN FAQ_CATEGORY ON FAQ.FAQ_CAT_NO = FAQ_CATEGORY.FAQ_CAT_NO ORDER BY FAQ.FAQ_NO DESC";
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
			String faqCatName = rs.getString("FAQ_CAT_NAME");
			
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
			vo.setFaqCatName(faqCatName);
			
			FAQArrList.add(vo);
			
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return FAQArrList;
	}

	public AdminFAQVo gerHitFAQ(Connection conn) throws Exception {

		
		String slq = "SELECT * FROM FAQ WHERE FAQ_STATUS = 'Y' ORDER BY FAQ_HIT DESC FETCH FIRST ROW ONLY";
		PreparedStatement pstmt = conn.prepareStatement(slq);
		ResultSet rs = pstmt.executeQuery();
		
		AdminFAQVo FAQHit = null;
		if (rs.next()) {
			
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
			
			
			FAQHit = new AdminFAQVo();
			FAQHit.setFaqNo(faqNo);
			FAQHit.setAdminNo(adminNo);
			FAQHit.setFaqCatNo(faqCatNo);
			FAQHit.setFaqTitle(faqTitle);
			FAQHit.setFaqQContent(faqQContent);
			FAQHit.setFaqAContent(faqAContent);
			FAQHit.setFaqStatus(faqStatus);
			FAQHit.setFaqHit(faqHit);
			FAQHit.setFaqEnrolldate(faqEnrolldate);
			FAQHit.setFaqModifydate(faqModifydate);
			
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return FAQHit;
	}

	public int editFAQ(Connection conn, AdminFAQVo vo) throws Exception {

		String sql = "UPDATE FAQ SET FAQ_CAT_NO = ? , FAQ_Q_CONTENT = ? , FAQ_A_CONTENT = ? , FAQ_MODIFYDATE = SYSDATE WHERE FAQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getFaqCatNo());
		pstmt.setString(2, vo.getFaqQContent());
		pstmt.setString(3, vo.getFaqAContent());
		pstmt.setString(4, vo.getFaqNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public AdminFAQVo updateFAQList(Connection conn, AdminFAQVo vo) throws Exception {
		
		String sql = "SELECT FAQ.*, FAQ_CATEGORY.FAQ_CAT_NAME FROM FAQ JOIN FAQ_CATEGORY ON FAQ.FAQ_CAT_NO = FAQ_CATEGORY.FAQ_CAT_NO WHERE FAQ.FAQ_NO = ? ORDER BY FAQ.FAQ_NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getFaqNo());
		ResultSet rs = pstmt.executeQuery();
		
		AdminFAQVo fvo = null;
		if (rs.next()) {
			
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
			String faqCatName = rs.getString("FAQ_CAT_NAME");
			
			fvo = new AdminFAQVo();
			fvo.setFaqNo(faqNo);
			fvo.setAdminNo(adminNo);
			fvo.setFaqCatNo(faqCatNo);
			fvo.setFaqTitle(faqTitle);
			fvo.setFaqQContent(faqQContent);
			fvo.setFaqAContent(faqAContent);
			fvo.setFaqStatus(faqStatus);
			fvo.setFaqHit(faqHit);
			fvo.setFaqEnrolldate(faqEnrolldate);
			fvo.setFaqModifydate(faqModifydate);
			fvo.setFaqCatName(faqCatName);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		
		return fvo;
	}

}
