package com.team4.skillmarket.admin.login.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team4.skillmarket.admin.login.vo.AdminLoginVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminLoginService {
	
	
	//로그인
	public void login(AdminLoginVo avo) throws SQLException {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String sql = "SELECT ADMIN_NO, FROM ADMIN WHERE ADMIN_ID = ? AND ADMIN_PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, avo.getAdminId());
		pstmt.setString(2, avo.getAdminPwd());
		ResultSet rs = pstmt.executeQuery();
		
		
		if(rs.next() == true) {
			String adminNo = rs.getString("ADMIN_NO");
			String adminGrNo = rs.getString("ADMIN_GR_NO");
			String adminId = rs.getString("ADMIN_ID");
			String adminPwd = rs.getString("ADMIN_PWD");
			String adminNick = rs.getString("ADMIN_NICK");
			
			avo.setAdminNo(adminNo);
			avo.setAdminGrNo(adminGrNo);
			avo.setAdminId(adminId);
			avo.setAdminPwd(adminPwd);
			avo.setAdminNick(adminNick);
		}
		
		else {
			
		}
		
	}

}

