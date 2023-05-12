package com.team4.skillmarket.admin.member.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.member.vo.memberListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class memberListService {

	public List<memberListVo> selectMemberList() throws SQLException {

		Connection conn = JDBCTemplate.getConnection();
		
		String slq = "SELECT * FROM MEMBER";
		PreparedStatement pstmt = conn.prepareStatement(slq);
		ResultSet rs = pstmt.executeQuery();
		
		List<memberListVo> memberArrList = new ArrayList<>();
		while(rs.next()) {
			
			String memberNo = rs.getString("MEMBER_NO");
			String statusNo = rs.getString("STATUS_NO");
			String memberId = rs.getString("MEMBER_ID");
			String memberPwd = rs.getString("MEMBER_PWD");
			String memberNick = rs.getString("MEMBER_NICK");
			String memberPhone = rs.getString("MEMBER_PHONE");
			String memberEmail = rs.getString("MEMBER_EMAIL");
			String memberInterst = rs.getString("MEMBER_INTERST");
			String memberSignDate = rs.getString("MEMBER_SIGN_DATE");
			String memberModifiDate = rs.getString("MEMBER_MODIFI_DATE");
			String memberBank = rs.getString("MEMBER_BANK");
			String memberAccount = rs.getString("MEMBER_ACCOUNT");
			String memberCash = rs.getString("MEMBER_CASH");
			String memberProfilePhoto = rs.getString("MEMBER_PROFILE_PHOTO");
			String memberNickStatus = rs.getString("MEMBER_NICK_STATUS");
			
			
			memberListVo mvo = new memberListVo();
			mvo.setMemberNo(memberNo);
			mvo.setStatusNo(statusNo);
			mvo.setMemberId(memberId);
			mvo.setMemberPwd(memberPwd);
			mvo.setMemberNick(memberNick);
			mvo.setMemberPhone(memberPhone);
			mvo.setMemberEmail(memberEmail);
			mvo.setMemberInterst(memberInterst);
			mvo.setMemberSignDate(memberSignDate);
			mvo.setMemberModifiDate(memberModifiDate);
			mvo.setMemberBank(memberBank);
			mvo.setMemberAccount(memberAccount);
			mvo.setMemberCash(memberCash);
			mvo.setMemberProfilePhoto(memberProfilePhoto);
			mvo.setMemberNickStatus(memberNickStatus);
			
			
			memberArrList.add(mvo);
			
		}
		
		JDBCTemplate.close(pstmt);
		
		JDBCTemplate.close(conn);
		
		
		return memberArrList;
	}

}

