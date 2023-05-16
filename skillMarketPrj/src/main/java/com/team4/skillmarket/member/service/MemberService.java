package com.team4.skillmarket.member.service;

import java.sql.Connection;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.member.dao.MemberDao;
import com.team4.skillmarket.member.vo.MemberVo;

public class MemberService {
	private final MemberDao dao = new MemberDao();

	public MemberVo login(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MemberVo loginMember = dao.login(conn, vo);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
		
		
	}

	public int join(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.join(conn, vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int checkId(String memberId) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.checkId(conn, memberId);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int checkNick(String memberNick) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.checkNick(conn, memberNick);
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int checkEmail(String memberEmail) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.checkEmail(conn, memberEmail);
		
		JDBCTemplate.close(conn);
		
		System.out.println(result);
		
		return result;
	}

}
