package com.team4.skillmarket.admin.home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.home.vo.HomeVo;
import com.team4.skillmarket.admin.home.vo.MonthStatsVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminHomeDao {

	public HomeVo getListByHome(Connection conn) throws Exception {
		
		HomeVo homeVo = null;
		
	    // FAQ 쿼리 실행
	    String sql = "SELECT F.*,FC.FAQ_CAT_NAME FROM FAQ F JOIN FAQ_CATEGORY FC ON F.FAQ_CAT_NO = FC.FAQ_CAT_NO WHERE F.FAQ_STATUS = 'Y' ORDER BY F.FAQ_HIT DESC FETCH FIRST ROW ONLY";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {
	        homeVo = new HomeVo();
	        homeVo.setFaqNo(rs.getString("FAQ_NO"));
	        homeVo.setFaqCatName(rs.getString("FAQ_CAT_NAME"));
	        homeVo.setAdminNoFAQ(rs.getString("ADMIN_NO"));
	        homeVo.setFaqCatNo(rs.getString("FAQ_CAT_NO"));
	        homeVo.setFaqTitle(rs.getString("FAQ_TITLE"));
	        homeVo.setFaqQContent(rs.getString("FAQ_Q_CONTENT"));
	        homeVo.setFaqAContent(rs.getString("FAQ_A_CONTENT"));
	        homeVo.setFaqStatus(rs.getString("FAQ_STATUS"));
	        homeVo.setFaqHit(rs.getString("FAQ_HIT"));
	        homeVo.setFaqEnrolldate(rs.getString("FAQ_ENROLLDATE"));
	        homeVo.setFaqModifydate(rs.getString("FAQ_MODIFYDATE"));
	    }
	    JDBCTemplate.close(pstmt);
	    JDBCTemplate.close(rs);

	    // NOTICE 쿼리 실행
	    sql = "SELECT * FROM (SELECT * FROM NOTICE WHERE NOTI_STATUS = 'N' ORDER BY NOTI_NO DESC) WHERE ROWNUM = 1";
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    if (rs.next()) {
	        if (homeVo == null) {
	            homeVo = new HomeVo();
	        }
	        homeVo.setNotiNo(rs.getString("NOTI_NO"));
	        homeVo.setNotiCatNo(rs.getString("NOTI_CAT_NO"));
	        homeVo.setAdminNoNotice(rs.getString("ADMIN_NO"));
	        homeVo.setNotiTitle(rs.getString("NOTI_TITLE"));
	        homeVo.setNotiContent(rs.getString("NOTI_CONTENT"));
	        homeVo.setNotiEnrolldate(rs.getString("NOTI_ENROLLDATE"));
	        homeVo.setNotiModifydate(rs.getString("NOTI_MODIFYDATE"));
	        homeVo.setNotiStatus(rs.getString("NOTI_STATUS"));
	        homeVo.setNotiHit(rs.getString("NOTI_HIT"));
	    }
	    JDBCTemplate.close(pstmt);
	    JDBCTemplate.close(rs);

	    // COUNT 쿼리 실행
	    sql = "SELECT (SELECT COUNT(*) FROM NOTICE WHERE NOTI_STATUS = 'N') AS NOTICE_COUNT, " +
	          "(SELECT COUNT(*) FROM REPORT WHERE RPT_STATUS = 'N') AS REPORT_COUNT, " +
	          "(SELECT COUNT(*) FROM QNA Q LEFT JOIN QUESTION_ANSWER A ON Q.QNA_NO = A.QNA_NO WHERE A.QUESTION_ANSWER_CONTENT IS NULL) AS NO_ANSWER_COUNT, " +
	          "(SELECT COUNT(*) FROM MEMBER WHERE STATUS_NO = 1) AS MEMBER_COUNT, " +
	          "(SELECT COUNT(M.STATUS_NO) FROM FREELANCER F INNER JOIN MEMBER M ON F.MEMBER_NO = M.MEMBER_NO) AS FREELANCER_COUNT " +
	          "FROM DUAL";
	    pstmt = conn.prepareStatement(sql);
	    rs = pstmt.executeQuery();
	    if (rs.next()) {
	        if (homeVo == null) {
	            homeVo = new HomeVo();
	        }
	        homeVo.setNoticeCount(rs.getInt("NOTICE_COUNT"));
	        homeVo.setReportCount(rs.getInt("REPORT_COUNT"));
	        homeVo.setNoAnswerCount(rs.getInt("NO_ANSWER_COUNT"));
	        homeVo.setMemberCount(rs.getInt("MEMBER_COUNT"));
	        homeVo.setFreelancerCount(rs.getInt("FREELANCER_COUNT"));
	    }
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		
		return homeVo;
	}

	public List<MonthStatsVo> getMonthlySalesAndSignupStats(Connection conn) throws Exception {
		
		String sql = "SELECT TO_CHAR(SL.ENROLL_DATE, 'YY/MM') AS MONTH, SUM(SL.SALES) AS TOTAL_SALES, COUNT(M.MEMBER_SIGN_DATE) AS SIGNUP_COUNT FROM SALES_LOG SL LEFT JOIN MEMBER M ON TO_CHAR(SL.ENROLL_DATE, 'YY/MM') = TO_CHAR(M.MEMBER_SIGN_DATE, 'YY/MM') GROUP BY TO_CHAR(SL.ENROLL_DATE, 'YY/MM') ORDER BY TO_CHAR(SL.ENROLL_DATE, 'YY/MM') DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<MonthStatsVo> monthStatsList =  new ArrayList<>();
		while(rs.next()) {
			String month = rs.getString("MONTH");
			String totalSales = rs.getString("TOTAL_SALES");
			String signupCount = rs.getString("SIGNUP_COUNT");
			
			MonthStatsVo vo = new MonthStatsVo();
			vo.setMonth(month);
			vo.setTotalSales(totalSales);
			vo.setSignupCount(signupCount);
			
			monthStatsList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return monthStatsList;
	}

	public Map<String, List<?>> getCategoryNameByHome(Connection conn) throws Exception {
		
		Map<String, List<?>> catNameMap = new HashMap<>();
		List<AdminFAQVo> faqNameList = new ArrayList<>();
		
		String sql="SELECT * FROM FAQ_CATEGORY";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			
			String faqCatNo = rs.getString("FAQ_CAT_NO");
			String faqCatName = rs.getString("FAQ_CAT_NAME");
			
			AdminFAQVo vo = new AdminFAQVo();
			vo.setFaqCatNo(faqCatNo);
			vo.setFaqCatName(faqCatName);
			
			faqNameList.add(vo);
		}
		
		
		
		
		
		return catNameMap;
	}
	

}
