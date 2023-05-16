package com.team4.skillmarket.admin.FAQ.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.FAQ.dao.AdminFAQDao;
import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.category.dao.AdminCatagoryDao;
import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminFAQService {

	public List<AdminFAQVo> selectFAQList() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		AdminFAQDao dao = new AdminFAQDao();
		List<AdminFAQVo> FAQArrList =  dao.selectFAQList(conn);
		
		JDBCTemplate.close(conn);
		
		return FAQArrList;
		
	}

}
