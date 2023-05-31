package com.team4.skillmarket.admin.category.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.category.dao.AdminCatagoryDao;
import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.admin.category.vo.CategoryEditVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminCatagoryService {

	private final AdminCatagoryDao dao = new AdminCatagoryDao();
	
	public List<AdminCategoryVo> selectCatList() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		
		List<AdminCategoryVo> catArrList =  dao.selectCatList(conn);
		
		return catArrList;
	}

	public int addCat(CategoryEditVo vo) {
		
		
		Connection conn = JDBCTemplate.getConnection();
		int result =  dao.addCat(vo,conn);
		
		return result;
	}
	
	
	
}
