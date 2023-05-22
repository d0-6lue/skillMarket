package com.team4.skillmarket.home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;

public class HomeDao {

	public List<EstimateCategoryVo> getCategory(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM ESTIMATE_CAT WHERE ESTIMATE_CAT_SCOPE = 1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<EstimateCategoryVo> cList = new ArrayList<>();
		while(rs.next()) {
			String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
			String aboveCatNo = rs.getString("ABOVE_CAT_NO");
			String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
			String estimateCatScope = rs.getString("ESTIMATE_CAT_SCOPE");
			String estimateCatFile = rs.getString("ESTIMATE_CAT_FILE");
			
			EstimateCategoryVo vo = new EstimateCategoryVo();
			
			vo.setEstimateCatNo(estimateCatNo);
			vo.setAboveCatNo(aboveCatNo);
			vo.setEstimateCatName(estimateCatName);
			vo.setEstimateCatScope(estimateCatScope);
			vo.setEstimateCatFile(estimateCatFile);
			
			cList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cList;
		
	}

}
