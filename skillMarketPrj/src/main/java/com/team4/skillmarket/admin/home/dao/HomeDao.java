package com.team4.skillmarket.admin.home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team4.skillmarket.admin.home.vo.HomeVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class HomeDao {

	public HomeVo getListByHome(Connection conn) throws Exception {
		
		// FAQ 쿼리
		String sql = "SELECT * FROM FAQ WHERE FAQ_STATUS = 'N' ORDER BY FAQ_HIT DESC FETCH FIRST ROW ONLY";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		// NOTICE 쿼리
		sql = "SELECT * FROM ( SELECT * FROM NOTICE WHERE NOTI_STATUS = 'N' ORDER BY NOTI_NO DESC ) WHERE ROWNUM = 1";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs2 = pstmt.executeQuery();
		 // COUNT 쿼리
		sql = "SELECT (SELECT COUNT(*) FROM NOTICE WHERE NOTI_STATUS = 'N') AS NOTICE_COUNT,(SELECT COUNT(*) FROM REPORT WHERE RPT_STATUS = 'N') AS REPORT_COUNT, (SELECT COUNT(*) FROM FAQ WHERE FAQ_STATUS = 'N') AS FAQ_COUNT, (SELECT COUNT(*) FROM MEMBER WHERE STATUS_NO = 1) AS MEMBER_COUNT, (SELECT COUNT(M.STATUS_NO) FROM FREELANCER F INNER JOIN MEMBER M ON F.MEMBER_NO = M.MEMBER_NO) AS FREELANCER_COUNT FROM DUAL";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs3 = pstmt.executeQuery();
		
		HomeVo homeVo = null;
		if (rs.next()&&rs2.next()&&rs3.next()) {
			// 인기 FAQ
			String faqNo = rs.getString("FAQ_NO");
			String adminNoFAQ = rs.getString("ADMIN_NO");
			String faqCatNo = rs.getString("FAQ_CAT_NO");
			String faqTitle = rs.getString("FAQ_TITLE");
			String faqQContent = rs.getString("FAQ_Q_CONTENT");
			String faqAContent = rs.getString("FAQ_A_CONTENT");
			String faqStatus = rs.getString("FAQ_STATUS");
			String faqHit = rs.getString("FAQ_HIT");
			String faqEnrolldate = rs.getString("FAQ_ENROLLDATE");
			String faqModifydate = rs.getString("FAQ_MODIFYDATE");
			
			// 최근 NOTICE
			String notiNo = rs2.getString("NOTI_NO");
			String notiCatNo = rs2.getString("NOTI_CAT_NO");
			String adminNoNotice = rs2.getString("ADMIN_NO");
			String notiTitle = rs2.getString("NOTI_TITLE");
			String notiContent = rs2.getString("NOTI_CONTENT");
			String notiEnrolldate = rs2.getString("NOTI_ENROLLDATE");
			String notiModifydate = rs2.getString("NOTI_MODIFYDATE");
			String notiStatus = rs2.getString("NOTI_STATUS");
			String notiHit = rs2.getString("NOTI_HIT");
			
			// COUNT
			int noticeCount = rs3.getInt("NOTICE_COUNT");
			int reportCount = rs3.getInt("REPORT_COUNT");
			int faqCount = rs3.getInt("FAQ_COUNT");
			int memberCount = rs3.getInt("MEMBER_COUNT");
			int freelancerCount = rs3.getInt("FREELANCER_COUNT");
			
			homeVo = new HomeVo();
			
			homeVo.setFaqNo(faqNo);
			homeVo.setAdminNoFAQ(adminNoFAQ);
			homeVo.setFaqCatNo(faqCatNo);
			homeVo.setFaqTitle(faqTitle);
			homeVo.setFaqQContent(faqQContent);
			homeVo.setFaqAContent(faqAContent);
			homeVo.setFaqStatus(faqStatus);
			homeVo.setFaqHit(faqHit);
			homeVo.setFaqEnrolldate(faqEnrolldate);
			homeVo.setFaqModifydate(faqModifydate);
			
			homeVo.setNotiNo(notiNo);
			homeVo.setNotiCatNo(notiCatNo);
			homeVo.setAdminNoNotice(adminNoNotice);
			homeVo.setNotiTitle(notiTitle);
			homeVo.setNotiContent(notiContent);
			homeVo.setNotiEnrolldate(notiEnrolldate);
			homeVo.setNotiModifydate(notiModifydate);
			homeVo.setNotiStatus(notiStatus);
			homeVo.setNotiHit(notiHit);
			
			homeVo.setNoticeCount(noticeCount);
			homeVo.setReportCount(reportCount);
			homeVo.setFaqCount(faqCount);
			homeVo.setMemberCount(memberCount);
			homeVo.setFreelancerCount(freelancerCount);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		JDBCTemplate.close(rs2);
		JDBCTemplate.close(rs3);
		
		return homeVo;
	}

}
