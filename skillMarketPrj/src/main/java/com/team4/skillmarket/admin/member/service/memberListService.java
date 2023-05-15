package com.team4.skillmarket.admin.member.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.member.dao.MemberListDao;
import com.team4.skillmarket.admin.member.vo.memberListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class memberListService {

	public List<memberListVo> selectMemberList() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		MemberListDao dao = new MemberListDao();
		List<memberListVo> memberArrList = dao.selectMemberList(conn);
		
		JDBCTemplate.close(conn);
		
		
		return memberArrList;
	}

}

