package com.team4.skillmarket.admin.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.member.vo.memberListVo;
import com.team4.skillmarket.admin.report.vo.reportListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class roportListDao {

	public List<reportListVo> selectReportList(Connection conn) throws Exception {

		String sql = "SELECT * FROM REPORT";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<reportListVo> reprotArrList =  new ArrayList<>();
		while(rs.next()) {
			
			String rptNo = rs.getString("RPT_NO");
			String rptcatNo = rs.getString("RPTCAT_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String memberNoBad = rs.getString("MEMBER_NO_BAD");
			String rptTitle = rs.getString("RPT_TITLE");
			String rptContent = rs.getString("RPT_CONTENT");
			String rptStatus = rs.getString("RPT_STATUS");
			
			reportListVo rvo = new reportListVo();
			rvo.setRptNo(rptNo);
			rvo.setRptcatNo(rptcatNo);
			rvo.setMemberNo(memberNo);
			rvo.setMemberNoBad(memberNoBad);
			rvo.setRptTitle(rptTitle);
			rvo.setRptContent(rptContent);
			rvo.setRptStatus(rptStatus);
			
			reprotArrList.add(rvo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return reprotArrList;
	}

}
