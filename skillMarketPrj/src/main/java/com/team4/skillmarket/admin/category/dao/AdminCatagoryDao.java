package com.team4.skillmarket.admin.category.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.admin.category.vo.CategoryEditVo;

public class AdminCatagoryDao {

	public List<AdminCategoryVo> selectCatList(Connection conn) throws Exception {

			String slq = "SELECT * FROM ESTIMATE_CAT";
			PreparedStatement pstmt = conn.prepareStatement(slq);
			ResultSet rs = pstmt.executeQuery();
			
			List<AdminCategoryVo> catArrList = new ArrayList<>();
			while (rs.next()) {
				
				String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
				String aboveCatNo = rs.getString("ABOVE_CAT_NO");
				String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
				String estimateCatScope = rs.getString("ESTIMATE_CAT_SCOPE");
				String estimateCatFile = rs.getString("ESTIMATE_CAT_FILE");
				
				AdminCategoryVo vo = new AdminCategoryVo();
				vo.setEstimateCatNo(estimateCatNo);
				vo.setAboveCatNo(aboveCatNo);
				vo.setEstimateCatName(estimateCatName);
				vo.setEstimateCatScope(estimateCatScope);
				vo.setEstimateCatFile(estimateCatFile);
				
				catArrList.add(vo);
				
			}
			
			return catArrList;
	}

	public int addCat(CategoryEditVo vo, Connection conn) {
		
//		INSERT INTO ESTIMATE_CAT(
//			    ESTIMATE_CAT_NO,
//			    ABOVE_CAT_NO,
//			    ESTIMATE_CAT_NAME,
//			    ESTIMATE_CAT_SCOPE
//			)
//			VALUES(
//			    
//
//			)
		
		String sql = "";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		
		return result;
	}
}
