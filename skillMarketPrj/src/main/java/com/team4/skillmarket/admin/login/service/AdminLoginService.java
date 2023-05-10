package com.team4.skillmarket.admin.login.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team4.skillmarket.admin.login.dao.LoginDao;
import com.team4.skillmarket.admin.login.vo.AdminLoginVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminLoginService {
	
	
	//로그인
	public ResultSet login(AdminLoginVo avo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		LoginDao dao = new LoginDao();
		ResultSet rs =  dao.login(conn, avo);
		
		JDBCTemplate.close(conn);
		
		return rs;
		
	}

}

