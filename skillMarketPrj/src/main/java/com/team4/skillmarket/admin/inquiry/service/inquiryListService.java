package com.team4.skillmarket.admin.inquiry.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.inquiry.dao.inquiryListDao;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;

import com.team4.skillmarket.common.db.JDBCTemplate;

public class inquiryListService {

	public List<inquiryListVo> selectInquiryList() throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		inquiryListDao dao = new inquiryListDao();
		List<inquiryListVo> inquiryArrList =  dao.selectInquiryList(conn);
		
		JDBCTemplate.close(conn);
		
		return inquiryArrList;
	}

}
