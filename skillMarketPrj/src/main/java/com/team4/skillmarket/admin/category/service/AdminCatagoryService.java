package com.team4.skillmarket.admin.category.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.category.dao.AdminCatagoryDao;
import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminCatagoryService {

	public List<AdminCategoryVo> selectCatList() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		AdminCatagoryDao dao = new AdminCatagoryDao();
		List<AdminCategoryVo> catArrList =  dao.selectCatList(conn);
		
		return catArrList;
	}
	
	
	
}
