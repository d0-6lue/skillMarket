package com.team4.skillmarket.member.service;

import java.sql.Connection;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.member.dao.MemberDao;
import com.team4.skillmarket.member.vo.MemberVo;

public class MemberService {

	public MemberVo login(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MemberDao dao = new MemberDao();
		
		MemberVo loginMember = dao.login(conn, vo);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
		
		
	}

	public int join(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MemberDao dao = new MemberDao();
		int result = dao.join(conn, vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
