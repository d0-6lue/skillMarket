package com.team4.skillmarket.admin.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminMemberDao {

	public int stopMember(String memberNo, Connection conn) throws Exception {
		
		String sql = "UPDATE MEMBER SET STATUS_NO = '3' WHERE MEMBER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNo);
		int stopMember = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return stopMember;
	}

}
