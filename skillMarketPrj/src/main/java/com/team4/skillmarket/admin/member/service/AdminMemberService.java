package com.team4.skillmarket.admin.member.service;

import java.sql.Connection;

import com.team4.skillmarket.admin.member.dao.AdminMemberDao;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminMemberService {
	
	private final AdminMemberDao dao = new AdminMemberDao();	
	
	public int stopMember(String memberNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int stopMember = dao.stopMember(memberNo,conn);
		
		if (stopMember == 1) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return stopMember;
	}

}
