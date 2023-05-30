package com.team4.skillmarket.admin.inquiry.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.inquiry.dao.inquiryListDao;
import com.team4.skillmarket.admin.inquiry.vo.InquiryVo;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class inquiryListService {
	
	private final inquiryListDao dao = new inquiryListDao();
	
	public List<inquiryListVo> selectInquiryList() throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		inquiryListDao dao = new inquiryListDao();
		List<inquiryListVo> inquiryArrList =  dao.selectInquiryList(conn);
		
		JDBCTemplate.close(conn);
		
		return inquiryArrList;
	}

	public List<inquiryListVo> selectInquiryCatList() throws Exception {
			
		Connection conn = JDBCTemplate.getConnection();
		
		List<inquiryListVo> inquiryCatList = dao.selectInquiryCatList(conn);
		
		JDBCTemplate.close(conn);
		
		return inquiryCatList;
	}

	public int answerInquiry(InquiryVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.answerInquiry(vo,conn);
		
		if (result==1&&dao.inquiryStatusUpdate(vo,conn) > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateAnswerInquiry(InquiryVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateAnswerInquiry(vo,conn);
		
		if (result==1&&dao.inquiryStatusUpdate(vo,conn) > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
