package com.team4.skillmarket.admin.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team4.skillmarket.admin.login.vo.AdminVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class LoginDao {

	public AdminVo login(Connection conn, AdminVo avo) throws Exception {

		String sql = "SELECT * FROM ADMIN WHERE ADMIN_ID = ? AND ADMIN_PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, avo.getAdminId());
		pstmt.setString(2, avo.getAdminPwd());
		ResultSet rs = pstmt.executeQuery();
		
		AdminVo adminVo = null;
		if(rs.next() == true) {
			String adminNo = rs.getString("ADMIN_NO");
			String adminGrNo = rs.getString("ADMIN_GR_NO");
			String adminId = rs.getString("ADMIN_ID");
			String adminPwd = rs.getString("ADMIN_PWD");
			String adminNick = rs.getString("ADMIN_NICK");
			
			adminVo = new AdminVo();
			adminVo.setAdminNo(adminNo);
			adminVo.setAdminGrNo(adminGrNo);
			adminVo.setAdminId(adminId);
			adminVo.setAdminPwd(adminPwd);
			adminVo.setAdminNick(adminNick);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return adminVo;
	}

}
