package com.team4.skillmarket.header.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;

public class HeaderDao {

	public List<EstimateCategoryVo> getEstimateCatList(Connection conn, String type) {
		
		List<EstimateCategoryVo> estimateCatVoList = new ArrayList<>();
		
		String getEstimateCategorySql = "SELECT ESTIMATE_CAT_NO, ABOVE_CAT_NO, ESTIMATE_CAT_NAME, ESTIMATE_CAT_SCOPE, ESTIMATE_CAT_FILE\r\n"
				+ "FROM ESTIMATE_CAT\r\n"
				+ "WHERE ESTIMATE_CAT_SCOPE = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(getEstimateCategorySql);
			if("big".equals(type)) {
				pstmt.setString(1, "1");
			}
			else if("mid".equals(type)) {
				pstmt.setString(1, "2");
			}
			else if("small".equals(type)) {
				pstmt.setString(1, "3");
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
				String aboveCatNo = rs.getString("ABOVE_CAT_NO");
				String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
				String estimateCatScope = rs.getString("ESTIMATE_CAT_SCOPE");
				String estimateCatFile = rs.getString("ESTIMATE_CAT_FILE");
				
				EstimateCategoryVo estivateCategoryVo = new EstimateCategoryVo();
				
				estivateCategoryVo.setEstimateCatNo(estimateCatNo);
				estivateCategoryVo.setAboveCatNo(aboveCatNo);
				estivateCategoryVo.setEstimateCatName(estimateCatName);
				estivateCategoryVo.setEstimateCatScope(estimateCatScope);
				estivateCategoryVo.setEstimateCatFile(estimateCatFile);
				
				estimateCatVoList.add(estivateCategoryVo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return estimateCatVoList;
	}

	

	public List<EstimateCategoryVo> getEstimateCatListByAboveNo(Connection conn, String type, String aboveNo) {
		List<EstimateCategoryVo> estimateCatVoList = new ArrayList<>();
		
		String getEstimateCategorySql = "SELECT ESTIMATE_CAT_NO, ABOVE_CAT_NO, ESTIMATE_CAT_NAME, ESTIMATE_CAT_SCOPE, ESTIMATE_CAT_FILE\r\n"
				+ "FROM ESTIMATE_CAT\r\n"
				+ "WHERE ESTIMATE_CAT_SCOPE = ? AND ABOVE_CAT_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(getEstimateCategorySql);
			if("mid".equals(type)) {
				pstmt.setString(1, "2");
			}
			else if("small".equals(type)) {
				pstmt.setString(1, "3");
			}
			pstmt.setString(2, aboveNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
				String aboveCatNo = rs.getString("ABOVE_CAT_NO");
				String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
				String estimateCatScope = rs.getString("ESTIMATE_CAT_SCOPE");
				String estimateCatFile = rs.getString("ESTIMATE_CAT_FILE");
				
				EstimateCategoryVo estivateCategoryVo = new EstimateCategoryVo();
				
				estivateCategoryVo.setEstimateCatNo(estimateCatNo);
				estivateCategoryVo.setAboveCatNo(aboveCatNo);
				estivateCategoryVo.setEstimateCatName(estimateCatName);
				estivateCategoryVo.setEstimateCatScope(estimateCatScope);
				estivateCategoryVo.setEstimateCatFile(estimateCatFile);
				
				estimateCatVoList.add(estivateCategoryVo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return estimateCatVoList;
	}
		
}
