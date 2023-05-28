package com.team4.skillmarket.admin.banner.service;

import java.sql.Connection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;

import com.team4.skillmarket.admin.banner.dao.BannerDao;
import com.team4.skillmarket.admin.banner.vo.BannerVo;
import com.team4.skillmarket.admin.home.dao.AdminHomeDao;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class BannerService {
	
	private final BannerDao dao = new BannerDao();
	
	public List<BannerVo> getBannerList() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		
		List<BannerVo> bannerList = dao.getBannerList(conn);
		
		bannerList = sortBannerList(bannerList);
		
		
		JDBCTemplate.close(conn);
		
		return bannerList;
	}

	public int insertBanner(BannerVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int insertBanner = dao.insertBanner(conn,vo);
		
		if (insertBanner == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return insertBanner;
	}

	public int updateBanner(BannerVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int updateBanner = dao.updateBanner(conn,vo);
		
		if (updateBanner == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return updateBanner;
	}

	public int checkBanner(BannerVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int checkBanner = dao.checkBanner(conn,vo);
		
		return checkBanner;
	}

	public int deletBanner(String no) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int deletBanner = dao.deletBanner(no,conn);
		
		if (deletBanner == 1) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return deletBanner;
	}
	
	
	// 주어진 리스트를 정렬하는 메소드
	public List<BannerVo> sortBannerList(List<BannerVo> bannerList) {
	    // 정렬 규칙에 따라 Comparator를 구현합니다.
	    Comparator<BannerVo> bannerComparator = new Comparator<BannerVo>() {
	        @Override
	        public int compare(BannerVo b1, BannerVo b2) {
	            // 비교 규칙을 정의합니다.
	            // 0을 가장 뒤로 보내고, 나머지 값들은 오름차순으로 정렬합니다.
	            if (Integer.parseInt(b1.getBannerNo()) == 0 && Integer.parseInt(b2.getBannerNo()) != 0) {
	                return 1;
	            } else if (Integer.parseInt(b1.getBannerNo()) != 0 && Integer.parseInt(b2.getBannerNo()) == 0) {
	                return -1;
	            } else {
	                return Integer.compare(Integer.parseInt(b1.getBannerNo()), Integer.parseInt(b2.getBannerNo()));
	            }
	        }
	    };

	    // 리스트를 정렬합니다.
	    Collections.sort(bannerList, bannerComparator);

	    return bannerList;
	}
	
}
