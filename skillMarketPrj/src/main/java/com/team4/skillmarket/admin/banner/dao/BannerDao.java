package com.team4.skillmarket.admin.banner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.banner.vo.BannerVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class BannerDao {

	public List<BannerVo> getBannerList(Connection conn) throws Exception {

		String sql = "SELECT * FROM BANNER ORDER BY BANNER_NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<BannerVo> bannerList = new ArrayList<>();
		while(rs.next()) {
			
			String bannerNo = rs.getString("BANNER_NO");
			String bannerBackgroundcolor = rs.getString("BANNER_BACKGROUNDCOLOR");
			String bannerFile = rs.getString("BANNER_FILE");
			String bannerEnrolldate = rs.getString("BANNER_ENROLLDATE");
			String bannerStatus = rs.getString("BANNER_STATUS");
			
			BannerVo vo = new BannerVo();
			vo.setBannerNo(bannerNo);
			vo.setBannerBackgroundcolor(bannerBackgroundcolor);
			vo.setBannerFile(bannerFile);
			vo.setBannerEnrolldate(bannerEnrolldate);
			vo.setBannerStatus(bannerStatus);
			
			bannerList.add(vo);
		}
		
		return bannerList;
	}

	
	
	public int insertBanner(Connection conn, BannerVo vo) throws Exception {

		String sql = "UPDATE BANNER SET BANNER_BACKGROUNDCOLOR = ?, BANNER_FILE = ? WHERE BANNER_NO = ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getBannerNo());
		pstmt.setString(2, vo.getBannerBackgroundcolor());
		pstmt.setString(3, vo.getBannerFile());
		int insertBanner = pstmt.executeUpdate();
		
		return insertBanner;
	}

	
	
	public int updateBanner(Connection conn, BannerVo vo) throws Exception {
		
		String sql = "UPDATE BANNER SET BANNER_BACKGROUNDCOLOR = ?, BANNER_FILE = ? , BANNER_STATUS = 'Y' WHERE BANNER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getBannerBackgroundcolor());
		pstmt.setString(2, vo.getBannerFile());
		pstmt.setString(3, vo.getBannerNo());
		int updateBanner = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return updateBanner;
	}
	


	public int checkBanner(Connection conn, BannerVo vo) throws Exception {
		
		String sql = "SELECT * FROM BANNER WHERE BANNER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getBannerNo());
		ResultSet rs = pstmt.executeQuery();
		
		
		int checkBanner = 0;
		if(rs.next()) {
			checkBanner = 1;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return checkBanner;
	}



	public int deletBanner(String no, Connection conn) throws Exception {
		
		String sql = "UPDATE BANNER SET BANNER_STATUS = 'X' WHERE BANNER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int deletBanner = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return deletBanner;
	}

}
