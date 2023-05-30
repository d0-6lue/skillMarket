package com.team4.skillmarket.csc.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.FAQ.vo.FAQCategoryVo;
import com.team4.skillmarket.admin.inquiry.vo.InquiryCategoryVo;
import com.team4.skillmarket.admin.inquiry.vo.InquiryVo;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.csc.dao.CSCDao;
import com.team4.skillmarket.member.vo.MemberVo;

public class CSCService {
	
		private final CSCDao dao = new CSCDao();

	public List<noticeListVo> getNotice() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<noticeListVo> noticeList = dao.getNotice(conn);
		
		JDBCTemplate.close(conn);
		
		return noticeList;
		
		
	}
	
	public List<noticeListVo> getRecentNotice() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<noticeListVo> noticeList = dao.getRecentNotice(conn);
		
		JDBCTemplate.close(conn);
		
		return noticeList;
		
		
	}

	public List<InquiryCategoryVo> getInquiryCategory() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<InquiryCategoryVo> inquiryCategoryList = dao.getInquiryCategory(conn);
		
		JDBCTemplate.close(conn);
		
		return inquiryCategoryList;
	}

	public int inquiry(inquiryListVo ivo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.inquiry(conn, ivo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public noticeListVo getNoticeByNo(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		noticeListVo noticeVo = dao.getNoticeByNo(conn,no);
		
		JDBCTemplate.close(conn);
		
		return noticeVo;
	}

	public int addHit(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.addHit(conn,no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public List<AdminFAQVo> getRecentFAQ() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<AdminFAQVo> faqList = dao.getRecentFAQ(conn);
		
		JDBCTemplate.close(conn);
		
		return faqList;
	}

	public List<AdminFAQVo> getFAQList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<AdminFAQVo> faqList = dao.getFAQList(conn);
		
		JDBCTemplate.close(conn);
		
		return faqList;
	}

	public List<FAQCategoryVo> getFAQCategoryList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<FAQCategoryVo> categoryList = dao.getFAQCategoryList(conn);
		
		JDBCTemplate.close(conn);
		
		return categoryList;
	}

	public AdminFAQVo getFAQByNo(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		AdminFAQVo fvo = dao.getFAQByNo(conn,no);
		
		JDBCTemplate.close(conn);
		
		return fvo;
		
	}

	public int addFAQHit(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.addFAQHit(conn,no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public List<InquiryVo> getInquiryList(MemberVo loginMember) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<InquiryVo> InquiryList = dao.getInquiryList(conn, loginMember);
		
		JDBCTemplate.close(conn);
		
		return InquiryList;
	}

	public InquiryVo getInquiryByNo(String no, MemberVo loginMember, String comment) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		InquiryVo ivo = dao.getInquiryByNo(conn,no, loginMember,comment);
		
		JDBCTemplate.close(conn);
		
		return ivo;
	}

	public String checkInquiryComment(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		String comment = dao.ckeckInquiryComment(conn, no);
		
		JDBCTemplate.close(conn);
		
		return comment;
		
	}

}
