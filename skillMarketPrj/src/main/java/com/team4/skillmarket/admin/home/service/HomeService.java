package com.team4.skillmarket.admin.home.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.home.dao.AdminHomeDao;
import com.team4.skillmarket.admin.home.vo.HomeVo;
import com.team4.skillmarket.admin.home.vo.MonthStatsVo;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class HomeService {
	
	private final AdminHomeDao dao = new AdminHomeDao();

	public HomeVo getListByHome() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		HomeVo homeVo =  dao.getListByHome(conn);
		
		JDBCTemplate.close(conn);
		
		return homeVo;
	}

	public List<MonthStatsVo> getMonthlySalesAndSignupStats() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		List<MonthStatsVo> monthStatsList = dao.getMonthlySalesAndSignupStats(conn);
		
		JDBCTemplate.close(conn);
		
		return monthStatsList;
	}

	public Map<String, List<?>> getCategoryNameByHome() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<AdminFAQVo> faqCatNameList = dao.getFAQCatName(conn);
		List<noticeListVo> noticeCatNameList = dao.getNoticeCatName(conn);
		
		Map<String, List<?>> catNameMap = new HashMap<>();
		catNameMap.put("FAQ", faqCatNameList);
		catNameMap.put("NOTICE", noticeCatNameList);
		
		JDBCTemplate.close(conn);
		
		return catNameMap;
	}

}
