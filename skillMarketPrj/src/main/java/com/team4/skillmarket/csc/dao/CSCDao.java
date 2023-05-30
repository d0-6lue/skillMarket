package com.team4.skillmarket.csc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.FAQ.vo.FAQCategoryVo;
import com.team4.skillmarket.admin.inquiry.vo.InquiryCategoryVo;
import com.team4.skillmarket.admin.inquiry.vo.InquiryVo;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.member.vo.MemberVo;

public class CSCDao {

	public List<noticeListVo> getNotice(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM NOTICE N JOIN NOTICE_CATEGORY C ON ( N.NOTI_CAT_NO = C.NOTI_CAT_NO) WHERE NOTI_STATUS = 'Y' ORDER BY NOTI_MODIFYDATE DESC";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<noticeListVo> noticeList = new ArrayList<>();
		while(rs.next()) {
			String notiNo = rs.getString("NOTI_NO");
			String notiCatNo = rs.getString("NOTI_CAT_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String notiTitle = rs.getString("NOTI_TITLE");
			String notiContent = rs.getString("NOTI_CONTENT");
			String notiEnrolldate = rs.getString("NOTI_ENROLLDATE");
			String notiModifydate = rs.getString("NOTI_MODIFYDATE");
			String notiStatus = rs.getString("NOTI_STATUS");
			String notiHit = rs.getString("NOTI_HIT");
			String notiCatName = rs.getString("NOTI_CAT_NAME");
			
			noticeListVo vo = new noticeListVo();
			
			vo.setNotiNo(notiNo);
			vo.setNotiCatNo(notiCatNo);
			vo.setAdminNo(adminNo);
			vo.setNotiTitle(notiTitle);
			vo.setNotiContent(notiContent);
			vo.setNotiEnrolldate(notiEnrolldate);
			vo.setNotiModifydate(notiModifydate);
			vo.setNotiStatus(notiStatus);
			vo.setNotiHit(notiHit);
			vo.setNotiCatName(notiCatName);
			
			noticeList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeList;
	}
	
	public List<noticeListVo> getRecentNotice(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM( SELECT * FROM NOTICE N JOIN NOTICE_CATEGORY C ON ( N.NOTI_CAT_NO = C.NOTI_CAT_NO) WHERE NOTI_STATUS = 'Y' ORDER BY NOTI_ENROLLDATE DESC) WHERE ROWNUM <= 4";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<noticeListVo> noticeList = new ArrayList<>();
		while(rs.next()) {
			String notiNo = rs.getString("NOTI_NO");
			String notiCatNo = rs.getString("NOTI_CAT_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String notiTitle = rs.getString("NOTI_TITLE");
			String notiContent = rs.getString("NOTI_CONTENT");
			String notiEnrolldate = rs.getString("NOTI_ENROLLDATE");
			String notiModifydate = rs.getString("NOTI_MODIFYDATE");
			String notiStatus = rs.getString("NOTI_STATUS");
			String notiHit = rs.getString("NOTI_HIT");
			String notiCatName = rs.getString("NOTI_CAT_NAME");
			
			noticeListVo vo = new noticeListVo();
			
			vo.setNotiNo(notiNo);
			vo.setNotiCatNo(notiCatNo);
			vo.setAdminNo(adminNo);
			vo.setNotiTitle(notiTitle);
			vo.setNotiContent(notiContent);
			vo.setNotiEnrolldate(notiEnrolldate);
			vo.setNotiModifydate(notiModifydate);
			vo.setNotiStatus(notiStatus);
			vo.setNotiHit(notiHit);
			vo.setNotiCatName(notiCatName);
			
			noticeList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeList;
	}

	public List<InquiryCategoryVo> getInquiryCategory(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM QNA_CATEGORY";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<InquiryCategoryVo> inquiryCategoryList = new ArrayList<>();
		while(rs.next()) {
			String qnaCatNo = rs.getString("QNA_CAT_NO");
			String qnaCatName = rs.getString("QNA_CAT_NAME");
			
			InquiryCategoryVo vo = new InquiryCategoryVo();
			
			vo.setQnaCatNo(qnaCatNo);
			vo.setQnaCatName(qnaCatName);
			
			inquiryCategoryList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return inquiryCategoryList;
	}

	public int inquiry(Connection conn, inquiryListVo ivo) throws Exception {
		
		String sql = "INSERT INTO QNA( QNA_NO , MEMBER_NO , QNA_CAT_NO , QNA_TITLE , QNA_CONTENT ) VALUES( SEQ_QNA.NEXTVAL, ?, ?, ?, ? )";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ivo.getMemberNo());
		pstmt.setString(2, ivo.getQnaCatNo());
		pstmt.setString(3, ivo.getQnaTitle());
		pstmt.setString(4, ivo.getQnaContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public noticeListVo getNoticeByNo(Connection conn, String no) throws Exception {
		
		String sql = "SELECT * FROM NOTICE WHERE NOTI_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		
		noticeListVo vo = null;
		if(rs.next()) {
			
			String notiNo = rs.getString("NOTI_NO");
			String notiCatNo = rs.getString("NOTI_CAT_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String notiTitle = rs.getString("NOTI_TITLE");
			String notiContent = rs.getString("NOTI_CONTENT");
			String notiEnrolldate = rs.getString("NOTI_ENROLLDATE");
			String notiModifydate = rs.getString("NOTI_MODIFYDATE");
			String notiStatus = rs.getString("NOTI_STATUS");
			String notiHit = rs.getString("NOTI_HIT");
			
			vo = new noticeListVo();
			
			vo.setNotiNo(notiNo);
			vo.setNotiCatNo(notiCatNo);
			vo.setAdminNo(adminNo);
			vo.setNotiTitle(notiTitle);
			vo.setNotiContent(notiContent);
			vo.setNotiEnrolldate(notiEnrolldate);
			vo.setNotiModifydate(notiModifydate);
			vo.setNotiStatus(notiStatus);
			vo.setNotiHit(notiHit);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
		
	}

	public int addHit(Connection conn, String no) throws Exception {
		String sql = "UPDATE NOTICE SET NOTI_HIT = NOTI_HIT + 1 WHERE NOTI_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public List<AdminFAQVo> getRecentFAQ(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM( SELECT * FROM FAQ F JOIN FAQ_CATEGORY C ON ( F.FAQ_CAT_NO = C.FAQ_CAT_NO) WHERE FAQ_STATUS = 'Y' ORDER BY FAQ_ENROLLDATE DESC) WHERE ROWNUM <= 4";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<AdminFAQVo> faqList = new ArrayList<>(); 
		while(rs.next()) {
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
			
			faqList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return faqList;
	}

	public List<AdminFAQVo> getFAQList(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM FAQ F JOIN FAQ_CATEGORY C ON ( F.FAQ_CAT_NO = C.FAQ_CAT_NO) WHERE FAQ_STATUS = 'Y' ORDER BY FAQ_ENROLLDATE DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<AdminFAQVo> faqList = new ArrayList<>(); 
		while(rs.next()) {
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
			
			faqList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return faqList;
		
	}

	public List<FAQCategoryVo> getFAQCategoryList(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM FAQ_CATEGORY";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<FAQCategoryVo> categoryList = new ArrayList<>();
		while(rs.next()) {
			String faqCatNo = rs.getString("FAQ_CAT_NO");
			String faqCatName =rs.getString("FAQ_CAT_NAME");
			
			FAQCategoryVo vo = new FAQCategoryVo();
			
			vo.setFaqCatNo(faqCatNo);
			vo.setFaqCatName(faqCatName);
			
			categoryList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return categoryList;
	}

	public AdminFAQVo getFAQByNo(Connection conn, String no) throws Exception {
		
		String sql = "SELECT * FROM FAQ F JOIN FAQ_CATEGORY C ON (F.FAQ_CAT_NO = C.FAQ_CAT_NO) WHERE FAQ_NO = ? AND FAQ_STATUS = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		AdminFAQVo fvo = null;
		if(rs.next()) {
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

	public int addFAQHit(Connection conn, String no) throws Exception {
		
		String sql = "UPDATE FAQ SET FAQ_HIT = FAQ_HIT + 1 WHERE FAQ_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public List<InquiryVo> getInquiryList(Connection conn, MemberVo loginMember) throws Exception {
		
		String sql = "SELECT QNA_NO, MEMBER_NO, QNA_CAT_NO, QNA_TITLE, QNA_CONTENT, TO_CHAR(QNA_ENROLLDATE,'YYYY\"년\"MM\"월\"DD\"일\"') AS QNA_ENROLLDATE, QNA_CAT_NAME FROM ( SELECT Q.QNA_NO, Q.MEMBER_NO, Q.QNA_CAT_NO, Q.QNA_TITLE, Q.QNA_CONTENT, Q.QNA_ENROLLDATE, C.QNA_CAT_NAME FROM QNA Q JOIN QNA_CATEGORY C ON Q.QNA_CAT_NO = C.QNA_CAT_NO WHERE Q.MEMBER_NO = ? AND QNA_STATUS = 'Y' ORDER BY QNA_ENROLLDATE DESC)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getMemberNo());
		ResultSet rs = pstmt.executeQuery();
		
		List<InquiryVo> inquiryList = new ArrayList<>();
		while(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String qnaCatNo = rs.getString("QNA_CAT_NO");
			String qnaTitle = rs.getString("QNA_TITLE");
			String qnaContent = rs.getString("QNA_CONTENT");
			String qnaEnrolldate = rs.getString("QNA_ENROLLDATE");
			String qnaCatName = rs.getString("QNA_CAT_NAME");
			
			
			InquiryVo vo = new InquiryVo();
			
			vo.setMemberNo(memberNo);
			vo.setQnaCatName(qnaCatName);
			vo.setQnaCatNo(qnaCatNo);
			vo.setQnaContent(qnaContent);
			vo.setQnaEnrolldate(qnaEnrolldate);
			vo.setQnaNo(qnaNo);
			vo.setQnaTitle(qnaTitle);
			
			inquiryList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return inquiryList;
	}

	public InquiryVo getInquiryByNo(Connection conn, String no, MemberVo loginMember, String comment) throws Exception {
		
		String sql = "SELECT Q.QNA_NO, Q.MEMBER_NO, Q.QNA_CAT_NO, Q.QNA_TITLE, Q.QNA_CONTENT, C.QNA_CAT_NAME, TO_CHAR(Q.QNA_ENROLLDATE,'YYYY\"년\"MM\"월\"DD\"일\"') AS QNA_ENROLLDATE, A.QUESTION_ANSWER_CONTENT FROM QNA Q JOIN QNA_CATEGORY C ON Q.QNA_CAT_NO = C.QNA_CAT_NO JOIN QUESTION_ANSWER A ON Q.QNA_NO = A.QNA_NO WHERE Q.QNA_NO = ? AND Q.MEMBER_NO = ? AND Q.QNA_STATUS = 'Y'";
		
		if(comment == null) {
			sql = "SELECT Q.QNA_NO, Q.MEMBER_NO, Q.QNA_CAT_NO, Q.QNA_TITLE, Q.QNA_CONTENT, C.QNA_CAT_NAME, TO_CHAR(Q.QNA_ENROLLDATE,'YYYY\"년\"MM\"월\"DD\"일\"') AS QNA_ENROLLDATE FROM QNA Q JOIN QNA_CATEGORY C ON Q.QNA_CAT_NO = C.QNA_CAT_NO WHERE Q.QNA_NO = ? AND Q.MEMBER_NO = ? AND Q.QNA_STATUS = 'Y'";
		}
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmt.setString(2, loginMember.getMemberNo());
		ResultSet rs = pstmt.executeQuery();
		
		InquiryVo ivo = null;
		while(rs.next()) {
			String qnaNo = rs.getString("QNA_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String qnaCatNo = rs.getString("QNA_CAT_NO");
			String qnaTitle = rs.getString("QNA_TITLE");
			String qnaContent = rs.getString("QNA_CONTENT");
			String qnaEnrolldate = rs.getString("QNA_ENROLLDATE");
			String qnaCatName = rs.getString("QNA_CAT_NAME");
			String questionAnswerContent = null;
			if(comment != null) {
				questionAnswerContent = rs.getString("QUESTION_ANSWER_CONTENT");
			}
			
			ivo = new InquiryVo();
			
			ivo.setMemberNo(memberNo);
			ivo.setQnaCatName(qnaCatName);
			ivo.setQnaCatNo(qnaCatNo);
			ivo.setQnaContent(qnaContent);
			ivo.setQnaEnrolldate(qnaEnrolldate);
			ivo.setQnaNo(qnaNo);
			ivo.setQnaTitle(qnaTitle);
			ivo.setQuestionAnswerContent(questionAnswerContent);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return ivo;
		
	}

	public String ckeckInquiryComment(Connection conn, String no) throws Exception {
		
		String sql = "SELECT QUESTION_ANSWER_CONTENT FROM QUESTION_ANSWER WHERE QNA_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		String comment = null;
		if(rs.next()) {
			comment = rs.getString("QUESTION_ANSWER_CONTENT");
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return comment;
	}

}
