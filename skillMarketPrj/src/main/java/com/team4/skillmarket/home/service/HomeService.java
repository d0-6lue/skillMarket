package com.team4.skillmarket.home.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.home.dao.HomeDao;

public class HomeService {

	private final HomeDao dao = new HomeDao();
	
	
	public List<EstimateCategoryVo> getCategory() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<EstimateCategoryVo> cList = dao.getCategory(conn);
		
		JDBCTemplate.close(conn);
		
		return cList;
	}


	public List<EstimateCategoryVo> getPopularCategoryList() throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<EstimateCategoryVo> pList = dao.getPopularCategoryList(conn);
		
		JDBCTemplate.close(conn);
		
		return pList;
	}

}
