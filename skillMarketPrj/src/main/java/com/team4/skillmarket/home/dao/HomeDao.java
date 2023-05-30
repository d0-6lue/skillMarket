package com.team4.skillmarket.home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.banner.vo.BannerVo;
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

	public List<EstimateCategoryVo> getPopularCategoryList(Connection conn) throws Exception {
		
		String sql = "SELECT ESTIMATE_CAT_NAME AS ESTIMATE_CAT_NAME, ESTIMATE_CAT_NO AS ESTIMATE_CAT_NO, ESTIMATE_CAT_FILE AS ESTIMATE_CAT_FILE, SUM(ESTIMATE_VIEWS) AS ESTIMATE_VIEWS FROM( SELECT C.ESTIMATE_CAT_NAME AS ESTIMATE_CAT_NAME, C.ESTIMATE_CAT_NO AS ESTIMATE_CAT_NO, E.ESTIMATE_VIEWS AS ESTIMATE_VIEWS, C.ESTIMATE_CAT_FILE FROM ESTIMATE_CAT C JOIN ESTIMATE E ON (C.ESTIMATE_CAT_NO = E.ESTIMATE_CAT_NO) WHERE ESTIMATE_CAT_SCOPE = '3' ) WHERE ROWNUM < 6 GROUP BY ESTIMATE_CAT_NAME, ESTIMATE_CAT_NO, ESTIMATE_CAT_FILE ORDER BY ESTIMATE_VIEWS DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<EstimateCategoryVo> pList = new ArrayList<>();
		while(rs.next()) {
			String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
			String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
			String estimateViews = rs.getString("ESTIMATE_VIEWS");
			String estimateCatFile = rs.getString("ESTIMATE_CAT_FILE");
			
			EstimateCategoryVo vo = new EstimateCategoryVo();
			vo.setEstimateCatNo(estimateCatNo);
			vo.setEstimateCatName(estimateCatName);
			vo.setEstimateCatFile(estimateCatFile);
			vo.setEstimateViews(estimateViews);
			
			pList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return pList;
		
	}

	public List<BannerVo> getBannerList(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM BANNER WHERE BANNER_STATUS = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<BannerVo> bList = new ArrayList<>();
		while(rs.next()) {
			String bannerNo = rs.getString("BANNER_NO");
			String bannerBackgroundcolor = rs.getString("BANNER_BACKGROUNDCOLOR");
			String bannerFile = rs.getString("BANNER_FILE");
			String bannerEnrolldate = rs.getString("BANNER_ENROLLDATE");
			String bannerStatus = rs.getString("BANNER_STATUS");
			
			BannerVo vo = new BannerVo();
			
			vo.setBannerBackgroundcolor(bannerBackgroundcolor);
			vo.setBannerEnrolldate(bannerEnrolldate);
			vo.setBannerFile(bannerFile);
			vo.setBannerNo(bannerNo);
			vo.setBannerStatus(bannerStatus);
			
			bList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return bList;
	}

}
