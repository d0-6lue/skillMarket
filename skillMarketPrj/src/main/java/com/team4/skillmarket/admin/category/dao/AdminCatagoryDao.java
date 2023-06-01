package com.team4.skillmarket.admin.category.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.admin.category.vo.CategoryEditVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

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
			
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
			
			return catArrList;
	}

	public int addCat(CategoryEditVo vo, Connection conn) throws Exception {
		
		
		String no="";
		String avo = "";
		String sql = "INSERT INTO ESTIMATE_CAT ( ESTIMATE_CAT_NO,ABOVE_CAT_NO,ESTIMATE_CAT_NAME,ESTIMATE_CAT_SCOPE,ESTIMATE_CAT_FILE ) VALUES (?, ?, ?, ?, NULL)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if ("small".equals(vo.getCheck())) {
			no = vo.getMeddle()+"50";
			avo = vo.getMeddle();
		}
		if ("meddle".equals(vo.getCheck())) {
			no = vo.getBig()+"50";
			avo = vo.getBig();
		}
		
		if ("big".equals(vo.getCheck())) {
			no = "50";
			avo = "0";
		}
		
		pstmt.setString(1, no);
		pstmt.setString(2, avo);
		pstmt.setString(3, vo.getName());
		pstmt.setString(4, vo.getScope());
		int result = pstmt.executeUpdate();		
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public List<AdminCategoryVo> rankCat(Connection conn) throws Exception {

		String sql = "SELECT EC.ESTIMATE_CAT_NO, EC.ESTIMATE_CAT_NAME, SUM(E.ESTIMATE_VIEWS) AS TOTAL_VIEWS FROM ESTIMATE_CAT EC JOIN ESTIMATE E ON EC.ESTIMATE_CAT_NO = E.ESTIMATE_CAT_NO GROUP BY EC.ESTIMATE_CAT_NO, EC.ESTIMATE_CAT_NAME ORDER BY TOTAL_VIEWS DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<AdminCategoryVo> rankCat =  new ArrayList<>();
		while(rs.next()) {
			
			String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
			String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
			String totalViews = rs.getString("TOTAL_VIEWS");
			
			AdminCategoryVo vo = new AdminCategoryVo();
			vo.setEstimateCatNo(estimateCatNo);
			vo.setEstimateCatName(estimateCatName);
			vo.setTotalViews(totalViews);
			
			rankCat.add(vo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return rankCat;
	}
}
