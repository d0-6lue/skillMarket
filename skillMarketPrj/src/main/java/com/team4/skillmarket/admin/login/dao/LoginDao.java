package com.team4.skillmarket.admin.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team4.skillmarket.admin.login.vo.AdminLoginVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class LoginDao {

	public ResultSet login(Connection conn, AdminLoginVo avo) throws Exception {

		String sql = "SELECT * FROM ADMIN WHERE ADMIN_ID = ? AND ADMIN_PWD = ?";
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
			throw new Exception("로그인중 정보 조회 실패");
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return rs;
	}

}
