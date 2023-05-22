package com.team4.skillmarket.csc.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.inquiry.vo.InquiryCategoryVo;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.csc.dao.CSCDao;

public class CSCService {
	
		private final CSCDao dao = new CSCDao();

	public List<noticeListVo> getNotice() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<noticeListVo> noticeList = dao.getNotice(conn);
		
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

}
