package com.team4.skillmarket.expert.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.expert.dao.ExpertDao;

public class ExpertService {
	private final ExpertDao dao = new ExpertDao();

	public List<EstimateCategoryVo> getSelectCategory() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<EstimateCategoryVo> categoryList = dao.getSelectCategory(conn);
		
		JDBCTemplate.close(conn);
		
		return categoryList;
		
	}

	
	
}
