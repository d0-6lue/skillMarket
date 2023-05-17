package com.team4.skillmarket.expert.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;

public class ExpertDao {

	public List<EstimateCategoryVo> getSelectCategory(Connection conn) throws Exception {
		
		String sql = "SELECT ESTIMATE_CAT_NO, ESTIMATE_CAT_NAME FROM ESTIMATE_CAT WHERE ESTIMATE_CAT_SCOPE = '1'";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<EstimateCategoryVo> categoryList = new ArrayList<>();
		while(rs.next()) {
			String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
			String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
			
			EstimateCategoryVo vo = new EstimateCategoryVo();
			
			vo.setEstimateCatNo(estimateCatNo);
			vo.setEstimateCatName(estimateCatName);
			
			categoryList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return categoryList;
		
	}
	
	

}
