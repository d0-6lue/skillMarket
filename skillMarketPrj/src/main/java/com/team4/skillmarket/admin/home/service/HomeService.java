package com.team4.skillmarket.admin.home.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.home.dao.HomeDao;
import com.team4.skillmarket.admin.home.vo.HomeVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class HomeService {

	public HomeVo getListByHome() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		HomeDao dao = new HomeDao();
		HomeVo homeVo =  dao.getListByHome(conn);
		
		JDBCTemplate.close(conn);
		
		return homeVo;
	}

}
