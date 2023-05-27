package com.team4.skillmarket.header.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.header.dao.HeaderDao;

public class HeaderService {

	private final HeaderDao headerDao = new HeaderDao();
	
	public List<EstimateCategoryVo> getEstimateCatList(String type) {

		Connection conn = JDBCTemplate.getConnection();
		
		List<EstimateCategoryVo> estimateCatVoList = headerDao.getEstimateCatList(conn, type);
		
		JDBCTemplate.close(conn);
		
		return estimateCatVoList;
	}
	

	public List<EstimateCategoryVo> getEstimateCatListByAboveNo(String type, String aboveNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<EstimateCategoryVo> estimateCatVoList = headerDao.getEstimateCatListByAboveNo(conn, type, aboveNo);
		
		JDBCTemplate.close(conn);
		
		return estimateCatVoList;
	}

}
