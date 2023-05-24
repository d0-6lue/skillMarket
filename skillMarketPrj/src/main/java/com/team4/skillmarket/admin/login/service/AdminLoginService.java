package com.team4.skillmarket.admin.login.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team4.skillmarket.admin.login.dao.LoginDao;
import com.team4.skillmarket.admin.login.vo.AdminVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminLoginService {
	
	
	//로그인
	public AdminVo login(AdminVo avo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		LoginDao dao = new LoginDao();
		AdminVo adminVo =  dao.login(conn,avo);
		
		JDBCTemplate.close(conn);
		
		return adminVo;
		
	}

}

